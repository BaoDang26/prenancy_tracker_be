package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.model.UserSubscription;
import com.fu.prenancytracker.model.enums.SubscriptionStatus;
import com.fu.prenancytracker.repository.UserSubscriptionRepository;
import com.fu.prenancytracker.service.UserSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscriptionServiceImpl(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    @Override
    public UserSubscription save(UserSubscription userSubscription) {
        return userSubscriptionRepository.save(userSubscription);
    }

    @Override
    public Iterable<UserSubscription> findAll() {
        return userSubscriptionRepository.findAll();
    }

    @Override
    public String generateSubscriptionCode() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); // Chỉ lấy số
        return uuid.substring(0, 15); // Lấy 15 số đầu tiên
    }

    @Override
    public boolean checkUserHasSubscription(Integer userID) {
        return userSubscriptionRepository.existsByUserIdAndStatus(userID, SubscriptionStatus.PENDING.name());
    }

    @Override
    public Optional<UserSubscription> findBySubscriptionCode(String subscriptionCode) {
        return userSubscriptionRepository.findBySubscriptionCode(subscriptionCode);
    }
}
