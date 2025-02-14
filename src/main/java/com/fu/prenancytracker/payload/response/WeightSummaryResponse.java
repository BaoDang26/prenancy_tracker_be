package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.FetalGrowthMeasurement;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link FetalGrowthMeasurement}
 */
@Value
public class WeightSummaryResponse implements Serializable {
    Integer id;
    Integer pregnancyProfileId;
    @NotNull
    Integer weekNumber;
    BigDecimal weight;
    String notes;
    @NotNull
    Instant createdDate;

    public WeightSummaryResponse(Integer id, Integer pregnancyProfileId, Integer weekNumber, BigDecimal weight, String notes, Instant createdDate) {
        this.id = id;
        this.pregnancyProfileId = pregnancyProfileId;
        this.weekNumber = weekNumber;
        this.weight = weight;
        this.notes = notes;
        this.createdDate = createdDate;
    }

    public WeightSummaryResponse(FetalGrowthMeasurement fetalGrowthMeasurement) {
        this.id = fetalGrowthMeasurement.getId();
        this.pregnancyProfileId = fetalGrowthMeasurement.getPregnancyProfile().getId();
        this.weekNumber = fetalGrowthMeasurement.getWeekNumber();
        this.weight = fetalGrowthMeasurement.getWeight();
        this.notes = fetalGrowthMeasurement.getNotes();
        this.createdDate = fetalGrowthMeasurement.getCreatedDate();
    }

    public Integer getId() {
        return id;
    }

    public Integer getPregnancyProfileId() {
        return pregnancyProfileId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}