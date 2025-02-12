package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.payload.request.CreateSubscriptionRequest;
import com.fu.prenancytracker.payload.response.PaymentUrlResponse;
import com.fu.prenancytracker.service.SubscriptionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    @Content(schema = @Schema(implementation = PaymentUrlResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createSubscriptionPlan(@RequestBody CreateSubscriptionRequest createSubscriptionRequest) {

        return ResponseEntity.ok(subscriptionPlanService.createSubscription(createSubscriptionRequest));
    }
}
