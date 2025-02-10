package com.fu.prenancytracker.repository;

import com.fu.prenancytracker.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {
}