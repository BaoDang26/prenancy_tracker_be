package com.fu.prenancytracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @NotNull
    @Column(name = "PriceMonthly", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceMonthly;

    @NotNull
    @Column(name = "PriceYear", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceYear;

    @Nationalized
    @Lob
    @Column(name = "Description")
    private String description;

    @NotNull
    @Column(name = "IsActive", nullable = false)
    private Boolean isActive = false;

    @NotNull
    @ColumnDefault("sysdatetime()")
    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    public SubscriptionPlan(Integer id, String name, String type, BigDecimal priceMonthly, BigDecimal priceYear, String description, Boolean isActive, Instant createdDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.priceMonthly = priceMonthly;
        this.priceYear = priceYear;
        this.description = description;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public SubscriptionPlan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPriceMonthly() {
        return priceMonthly;
    }

    public void setPriceMonthly(BigDecimal priceMonthly) {
        this.priceMonthly = priceMonthly;
    }

    public BigDecimal getPriceYear() {
        return priceYear;
    }

    public void setPriceYear(BigDecimal priceYear) {
        this.priceYear = priceYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}