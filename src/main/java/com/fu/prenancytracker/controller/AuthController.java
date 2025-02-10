package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.exception.TokenException;
import com.fu.prenancytracker.model.RefreshToken;
import com.fu.prenancytracker.payload.request.LoginRequest;
import com.fu.prenancytracker.payload.response.AccessTokenResponse;
import com.fu.prenancytracker.payload.response.LoginResponse;
import com.fu.prenancytracker.payload.response.MessageResponse;
import com.fu.prenancytracker.security.CustomUserDetails;
import com.fu.prenancytracker.security.JwtUtils;
import com.fu.prenancytracker.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Authentication", description = "Authentication management APIs")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
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
}
