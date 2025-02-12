package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.exception.EntityNotFoundException;
import com.fu.prenancytracker.model.SubscriptionPlan;
import com.fu.prenancytracker.repository.SubscriptionPlanRepository;
import com.fu.prenancytracker.service.SubscriptionPlanService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlanServiceImpl(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    @Override
    public SubscriptionPlan save(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public Iterable<SubscriptionPlan> findAll() {
        return subscriptionPlanRepository.findAll();
    }

    @Override
    public SubscriptionPlan findById(Integer planId) {
        return subscriptionPlanRepository.findById(planId).orElseThrow(() -> new EntityNotFoundException("Subscription plan id{ " + planId + "} not found"));

    }
}
