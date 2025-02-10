package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.model.PregnancyProfile;
import com.fu.prenancytracker.payload.request.CreatePregnancyProfileRequest;
import com.fu.prenancytracker.payload.response.PregnancyProfileResponse;
import com.fu.prenancytracker.security.CustomUserDetails;
import com.fu.prenancytracker.service.PregnancyProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Pregnancy Profiles", description = "Endpoints for managing pregnancy profiles")
@RestController
@RequestMapping("/api/pregnancy-profiles")
public class PregnancyProfileController {

    private final PregnancyProfileService pregnancyProfileService;

    public PregnancyProfileController(PregnancyProfileService pregnancyProfileService) {
        this.pregnancyProfileService = pregnancyProfileService;
    }

    @Operation(summary = "Create a new pregnancy profile", description = "Stores a new pregnancy profile and returns the created profile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile created successfully", content = {
                    @Content(schema = @Schema(implementation = PregnancyProfileResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPregnancyProfile(@RequestBody CreatePregnancyProfileRequest profileRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PregnancyProfile profile = new PregnancyProfile();
        profile.setNickName(profileRequest.getNickName());
        profile.setDueDate(profileRequest.getDueDate());
        profile.setConceptionDate(profileRequest.getConceptionDate());
        profile.setLastPeriodDate(profileRequest.getLastPeriodDate());
        profile.setPregnancyWeek(profileRequest.getPregnancyWeek());
        profile.setNotes(profileRequest.getNotes());
        profile.setStatus(profileRequest.getStatus());
        profile.setCreatedDate(Instant.now());
        profile.setUser(userDetails.getUser());

        PregnancyProfile createdProfile = pregnancyProfileService.createPregnancyProfile(profile);

        PregnancyProfileResponse pregnancyProfileResponse = new PregnancyProfileResponse(
                createdProfile.getId(),
                createdProfile.getNickName(),
                createdProfile.getDueDate(),
                createdProfile.getConceptionDate(),
                createdProfile.getLastPeriodDate(),
                createdProfile.getPregnancyWeek(),
                createdProfile.getNotes(),
                createdProfile.getStatus(),
                createdProfile.getCreatedDate(),
                createdProfile.getUser().getId()
        );
        return ResponseEntity.ok(pregnancyProfileResponse);
    }

//    @Operation(summary = "Update an existing pregnancy profile", description = "Updates the details of an existing pregnancy profile by ID")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid input data"),
//            @ApiResponse(responseCode = "404", description = "Profile not found"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @PutMapping("/update")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<PregnancyProfile> updatePregnancyProfile(
//            @RequestBody UpdatePregnancyProfileRequest updatePregnancyProfileRequest) {
//
//        PregnancyProfile updatedProfile = pregnancyProfileService.updatePregnancyProfile(profile);
//
//        return ResponseEntity.ok(updatedProfile);
//    }

    @Operation(summary = "Get a pregnancy profile", description = "Retrieves a specific pregnancy profile by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PregnancyProfileResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Profile not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{pregnancyProfileId}")
    public ResponseEntity<?> getPregnancyProfile(@PathVariable Integer pregnancyProfileId) {
        Optional<PregnancyProfile> profile = pregnancyProfileService.getPregnancyProfileById(pregnancyProfileId);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PregnancyProfileResponse pregnancyProfileResponse = new PregnancyProfileResponse(
                profile.get().getId(),
                profile.get().getNickName(),
                profile.get().getDueDate(),
                profile.get().getConceptionDate(),
                profile.get().getLastPeriodDate(),
                profile.get().getPregnancyWeek(),
                profile.get().getNotes(),
                profile.get().getStatus(),
                profile.get().getCreatedDate(),
                profile.get().getUser().getId()
        );
        return ResponseEntity.ok(pregnancyProfileResponse);
    }

    @Operation(summary = "Get all pregnancy profiles", description = "Retrieves a list of all pregnancy profiles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profiles retrieved successfully", content = {
                    @Content(schema = @Schema(implementation = PregnancyProfileResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Profile not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllPregnancyProfiles() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Iterable<PregnancyProfile> profiles = pregnancyProfileService.getAllPregnancyProfilesOfUser(userDetails.getUser().getId());
        if (!profiles.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }

        List<PregnancyProfileResponse> profileResponses = new ArrayList<>();
        profiles.forEach(profile -> profileResponses.add(new PregnancyProfileResponse(
                profile.getId(),
                profile.getNickName(),
                profile.getDueDate(),
                profile.getConceptionDate(),
                profile.getLastPeriodDate(),
                profile.getPregnancyWeek(),
                profile.getNotes(),
                profile.getStatus(),
                profile.getCreatedDate(),
                profile.getUser().getId()
        )));
        return ResponseEntity.ok(profileResponses);
    }
}
