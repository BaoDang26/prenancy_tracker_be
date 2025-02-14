package com.fu.prenancytracker.payload.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.fu.prenancytracker.model.UserSubscription}
 */
@Value
public class CreateSubscriptionRequest implements Serializable {
    Integer planId;
    @NotNull(message = "Amount not null")
    @Digits(message = "Amount is ", integer = 8, fraction = 2)
    BigDecimal amount;

    public Integer getPlanId() {
        return planId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}