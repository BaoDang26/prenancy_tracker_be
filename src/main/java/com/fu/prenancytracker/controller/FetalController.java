package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.model.FetalGrowthMeasurement;
import com.fu.prenancytracker.model.PregnancyProfile;
import com.fu.prenancytracker.payload.request.CreateFetalGrowthMeasurementRequest;
import com.fu.prenancytracker.payload.response.FetalGrowthMeasurementResponse;
import com.fu.prenancytracker.security.CustomUserDetails;
import com.fu.prenancytracker.service.FetalGrowthMeasurementService;
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

@Tag(name = "Fetal Growth Measurements", description = "Endpoints for managing fetal growth measurements")
@RestController
@RequestMapping("/api/fetal")
public class FetalController {
    private final FetalGrowthMeasurementService fetalGrowthMeasurementService;
    private final PregnancyProfileService pregnancyProfileService;

    public FetalController(FetalGrowthMeasurementService fetalGrowthMeasurementService, PregnancyProfileService pregnancyProfileService) {
        this.fetalGrowthMeasurementService = fetalGrowthMeasurementService;
        this.pregnancyProfileService = pregnancyProfileService;
    }


    @Operation(summary = "Create a new fetal growth measurement", description = "Stores a new measurement record for fetal growth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Measurement created successfully", content = {
                    @Content(schema = @Schema(implementation = FetalGrowthMeasurementResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createFetalGrowthMeasurement(@RequestBody CreateFetalGrowthMeasurementRequest request) {

        Optional<PregnancyProfile> pregnancyProfile = pregnancyProfileService.findByID(request.getPregnancyProfileId());

        if (pregnancyProfile.isEmpty()) {
            return ResponseEntity.badRequest().body("Pregnancy profile not found");
        }

        FetalGrowthMeasurement measurement = new FetalGrowthMeasurement();
        measurement.setPregnancyProfile(pregnancyProfile.get());
        measurement.setWeekNumber(request.getWeekNumber());
        measurement.setMeasurementDate(request.getMeasurementDate());
        measurement.setWeight(request.getWeight());
        measurement.setHeight(request.getHeight());
        measurement.setHeadCircumference(request.getHeadCircumference());
        measurement.setBellyCircumference(request.getBellyCircumference());
        measurement.setHeartRate(request.getHeartRate());
        measurement.setMovementCount(request.getMovementCount());
        measurement.setNotes(request.getNotes());
        measurement.setCreatedDate(Instant.now());

        FetalGrowthMeasurement createdMeasurement = fetalGrowthMeasurementService.save(measurement);
        return ResponseEntity.ok(new FetalGrowthMeasurementResponse(createdMeasurement));
    }

    @Operation(summary = "Get a fetal growth measurement", description = "Retrieves a specific measurement record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FetalGrowthMeasurementResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Measurement not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{measurementId}")
    public ResponseEntity<?> getFetalGrowthMeasurement(@PathVariable Integer measurementId) {
        FetalGrowthMeasurement measurement = fetalGrowthMeasurementService.getMeasurementById(measurementId);
        return measurement != null ? ResponseEntity.ok(new FetalGrowthMeasurementResponse(measurement)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all fetal growth measurements", description = "Retrieves all fetal growth measurements for the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Measurements retrieved successfully", content = {
                    @Content(schema = @Schema(implementation = FetalGrowthMeasurementResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllFetalGrowthMeasurements() {
        Iterable<FetalGrowthMeasurement> measurements = fetalGrowthMeasurementService.findAll();

        List<FetalGrowthMeasurementResponse> response = new ArrayList<>();
        measurements.forEach(measurement -> response.add(new FetalGrowthMeasurementResponse(measurement)));

        return ResponseEntity.ok(response);
    }

}
