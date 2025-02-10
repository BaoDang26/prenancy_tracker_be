package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.exception.TokenException;
import com.fu.prenancytracker.model.RefreshToken;
import com.fu.prenancytracker.model.Role;
import com.fu.prenancytracker.model.User;
import com.fu.prenancytracker.payload.request.LoginRequest;
import com.fu.prenancytracker.payload.request.RegisterRequest;
import com.fu.prenancytracker.payload.response.AccessTokenResponse;
import com.fu.prenancytracker.payload.response.LoginResponse;
import com.fu.prenancytracker.payload.response.MessageResponse;
import com.fu.prenancytracker.payload.response.UserResponse;
import com.fu.prenancytracker.security.CustomUserDetails;
import com.fu.prenancytracker.security.JwtUtils;
import com.fu.prenancytracker.service.RefreshTokenService;
import com.fu.prenancytracker.service.RoleService;
import com.fu.prenancytracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;


@Tag(name = "Authentication", description = "Authentication management APIs")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, RoleService roleService, UserService userService, PasswordEncoder passwordEncoder1) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder1;
    }

    @Operation(summary = "login by phone number and password", description = "Authenticate accounts by phone number and password. Returned will be account information and will not include a password", tags = {
            "Authentication"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = LoginResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {
                    @Content(schema = @Schema())})})
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        // Authenticated
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate mã jwt
        String accessToken = jwtUtils.generateJwtToken(loginRequest.getEmail());

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUser().getId());

        return ResponseEntity.ok(new LoginResponse(
                userDetails.getUser().getId(),
                userDetails.getUsername(),
                userDetails.getUser().getAvatarUrl(),
                userDetails.getUser().getRole().getRoleName(),
                refreshToken.getToken(),
                accessToken));
    }


    @Operation(summary = "Refresh token", description = "Using refresh token to get new access token when expired", tags = {
            "Authentication"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AccessTokenResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {
                    @Content(schema = @Schema())})})
    @PostMapping("/get-new-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestParam String refreshTokenRequest) {

        return refreshTokenService.findByToken(refreshTokenRequest)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(account -> {
                    String accessToken = jwtUtils.generateJwtToken(account.getEmail());
                    return ResponseEntity.ok(new AccessTokenResponse(accessToken, jwtUtils.getExpirationDate(accessToken)));
                })
                .orElseThrow(() -> new TokenException(refreshTokenRequest,
                        "Refresh token is not in database!"));
    }

    @Operation(summary = "Log out", description = "Log out of the system", tags = {"Authentication"})
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {
                    @Content(schema = @Schema())})})
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (userDetails != null) {
            // lấy user id từ context
            Integer userID = userDetails.getUser().getId();
            // gọi refreshTokenService xóa refresh token
            refreshTokenService.deleteByUserID(userID);
        }

        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

    @Operation(summary = "Register", description = "Register account of the system (default role USER)", tags = {"Authentication"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {
                    @Content(schema = @Schema())})})
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        Optional<Role> role = roleService.findByRoleName("ROLE_USER");

        if (role.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setAddress(registerRequest.getAddress());
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setRole(role.get());
        user.setCreatedDate(Instant.now());
        user.setStatus("Active");
        user.setEmailVerified(true);

        User userCreated = userService.save(user);
        UserResponse userResponse = new UserResponse(userCreated);

        return ResponseEntity.ok(userResponse);
    }
}
