package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.exception.EntityNotFoundException;
import com.fu.prenancytracker.model.SubscriptionPlan;
import com.fu.prenancytracker.payload.request.CreateSubscriptionPlanRequest;
import com.fu.prenancytracker.repository.SubscriptionPlanRepository;
import com.fu.prenancytracker.service.SubscriptionPlanService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

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
    public Optional<SubscriptionPlan> findById(Integer planId) {
        return subscriptionPlanRepository.findById(planId);

    }

    @Override
    public SubscriptionPlan createSubscriptionPlan(CreateSubscriptionPlanRequest createSubscriptionRequest) {
        // Create new subscription plan
        SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
        subscriptionPlan.setName(createSubscriptionRequest.getName());
        subscriptionPlan.setPrice(createSubscriptionRequest.getPrice());
        subscriptionPlan.setDuration(createSubscriptionRequest.getDuration());
        subscriptionPlan.setDescription(createSubscriptionRequest.getDescription());
        subscriptionPlan.setActive(true);
        subscriptionPlan.setCreatedDate(Instant.now());


        return save(subscriptionPlan);
    }
}
