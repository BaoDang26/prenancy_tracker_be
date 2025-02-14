package com.fu.prenancytracker.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.fu.prenancytracker.model.SubscriptionPlan}
 */
@Value
public class CreateSubscriptionPlanRequest implements Serializable {
    @NotNull
    @Size(message = "Max 100 character", max = 100)
    String name;
    @NotNull
    BigDecimal price;
    @NotNull
    @Positive(message = "Duration is a positive integer")
    Integer duration;
    String description;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}