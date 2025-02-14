package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.UserSubscription;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.UserSubscription}
 */
@Value
public class UserSubscriptionResponse implements Serializable {
    Integer id;
    String subscriptionPlanName;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal amount;
    String subscriptionCode;
    String bankCode;
    String transactionNo;
    Instant paymentDate;
    String status;
    Instant createdDate;

    public UserSubscriptionResponse(Integer id, String subscriptionPlanName, LocalDate startDate, LocalDate endDate, BigDecimal amount, String subscriptionCode, String bankCode, String transactionNo, Instant paymentDate, String status, Instant createdDate) {
        this.id = id;
        this.subscriptionPlanName = subscriptionPlanName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.subscriptionCode = subscriptionCode;
        this.bankCode = bankCode;
        this.transactionNo = transactionNo;
        this.paymentDate = paymentDate;
        this.status = status;
        this.createdDate = createdDate;
    }

    public UserSubscriptionResponse(UserSubscription userSubscription) {
        this.id = userSubscription.getId();
        this.subscriptionPlanName = userSubscription.getSubscriptionPlan().getName();
        this.startDate = userSubscription.getStartDate();
        this.endDate = userSubscription.getEndDate();
        this.amount = userSubscription.getAmount();
        this.subscriptionCode = userSubscription.getSubscriptionCode();
        this.bankCode = userSubscription.getBankCode();
        this.transactionNo = userSubscription.getTransactionNo();
        this.paymentDate = userSubscription.getPaymentDate();
        this.status = userSubscription.getStatus();
        this.createdDate = userSubscription.getCreatedDate();
    }

    public Integer getId() {
        return id;
    }

    public String getSubscriptionPlanName() {
        return subscriptionPlanName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSubscriptionCode() {
        return subscriptionCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}