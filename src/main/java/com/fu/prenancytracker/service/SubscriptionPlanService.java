package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.SubscriptionPlan;

public interface SubscriptionPlanService extends GeneralService<SubscriptionPlan>{

    SubscriptionPlan findById(Integer planId);
}
