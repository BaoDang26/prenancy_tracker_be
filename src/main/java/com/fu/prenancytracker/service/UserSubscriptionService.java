package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.UserSubscription;

import java.util.Optional;

public interface UserSubscriptionService extends  GeneralService<UserSubscription> {

    String generateSubscriptionCode();

    boolean checkUserHasSubscription(Integer userID);

    Optional<UserSubscription> findBySubscriptionCode(String subscriptionCode);
}
