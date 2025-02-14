package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.exception.EntityNotFoundException;
import com.fu.prenancytracker.model.SubscriptionPlan;
import com.fu.prenancytracker.payload.request.CreateSubscriptionPlanRequest;
import com.fu.prenancytracker.payload.response.SubscriptionPlanResponse;
import com.fu.prenancytracker.service.SubscriptionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Subscription Plan", description = "Subscription Plan API")
@RestController
@RequestMapping("api/subscription-plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

    @Operation(summary = "Create subscription plan", description = "Create subscription plan for user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create subscription plan successfully", content = {
                    @Content(schema = @Schema(implementation = SubscriptionPlanResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createSubscriptionPlan(@RequestBody CreateSubscriptionPlanRequest createSubscriptionRequest) {

        SubscriptionPlan subscriptionPlan = subscriptionPlanService.createSubscriptionPlan(createSubscriptionRequest);

        if (subscriptionPlan == null) {
            return ResponseEntity.badRequest().build();
        }

        SubscriptionPlanResponse response = new SubscriptionPlanResponse(subscriptionPlan);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all subscription plan", description = "Get all subscription plan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get all subscription plan successfully", content = {
                    @Content(schema = @Schema(implementation = SubscriptionPlanResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllSubscriptionPlan() {
        Iterable<SubscriptionPlan> subscriptionPlans = subscriptionPlanService.findAll();

        List<SubscriptionPlanResponse> response = new ArrayList<>();
        for (SubscriptionPlan subscriptionPlan : subscriptionPlans) {
            response.add(new SubscriptionPlanResponse(subscriptionPlan));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get subscription plan by id", description = "Get subscription plan by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get subscription plan by id successfully", content = {
                    @Content(schema = @Schema(implementation = SubscriptionPlanResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{subscriptionPlanId}")
    public ResponseEntity<?> getSubscriptionPlanById(@PathVariable Integer subscriptionPlanId) {
        // Find subscription plan by id or throw exception if not found
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(subscriptionPlanId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan id{ " + subscriptionPlanId + "} not found"));

        // Create response
        SubscriptionPlanResponse response = new SubscriptionPlanResponse(subscriptionPlan);
        return ResponseEntity.ok(response);
    }
}
