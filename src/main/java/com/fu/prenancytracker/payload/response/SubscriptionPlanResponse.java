package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.SubscriptionPlan;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.fu.prenancytracker.model.SubscriptionPlan}
 */
@Value
public class SubscriptionPlanResponse implements Serializable {
    Integer id;
    String name;
    BigDecimal price;
    Integer duration;
    String description;
    Boolean isActive;
    Instant createdDate;

    public SubscriptionPlanResponse(Integer id, String name, BigDecimal price, Integer duration, String description, Boolean isActive, Instant createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public SubscriptionPlanResponse(SubscriptionPlan subscriptionPlan) {
        this.id = subscriptionPlan.getId();
        this.name = subscriptionPlan.getName();
        this.price = subscriptionPlan.getPrice();
        this.duration = subscriptionPlan.getDuration();
        this.description = subscriptionPlan.getDescription();
        this.isActive = subscriptionPlan.getActive();
        this.createdDate = subscriptionPlan.getCreatedDate();
    }

    public Integer getId() {
        return id;
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}