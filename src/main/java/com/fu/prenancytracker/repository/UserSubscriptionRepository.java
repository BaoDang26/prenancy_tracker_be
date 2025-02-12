package com.fu.prenancytracker.repository;

import com.fu.prenancytracker.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {
    boolean existsByUserIdAndStatus(Integer userID, String subscriptionStatus);

    Optional<UserSubscription> findBySubscriptionCode(String subscriptionCode);
}