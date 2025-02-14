package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.SubscriptionPlan;
import com.fu.prenancytracker.payload.request.CreateSubscriptionPlanRequest;

import java.util.Optional;

public interface SubscriptionPlanService extends GeneralService<SubscriptionPlan>{

    Optional<SubscriptionPlan> findById(Integer planId);

    SubscriptionPlan createSubscriptionPlan(CreateSubscriptionPlanRequest createSubscriptionRequest);
}
