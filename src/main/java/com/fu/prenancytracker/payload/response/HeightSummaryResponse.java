package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.FetalGrowthMeasurement;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.fu.prenancytracker.model.FetalGrowthMeasurement}
 */
@Value
public class HeightSummaryResponse implements Serializable {
    Integer id;
    Integer pregnancyProfileId;
    @NotNull
    Integer weekNumber;
    BigDecimal height;
    String notes;
    @NotNull
    Instant createdDate;

    public HeightSummaryResponse(Integer id, Integer pregnancyProfileId, Integer weekNumber, BigDecimal height, String notes, Instant createdDate) {
        this.id = id;
        this.pregnancyProfileId = pregnancyProfileId;
        this.weekNumber = weekNumber;
        this.height = height;
        this.notes = notes;
        this.createdDate = createdDate;
    }

    public HeightSummaryResponse(FetalGrowthMeasurement fetalGrowthMeasurement) {
        this.id = fetalGrowthMeasurement.getId();
        this.pregnancyProfileId = fetalGrowthMeasurement.getPregnancyProfile().getId();
        this.weekNumber = fetalGrowthMeasurement.getWeekNumber();
        this.height = fetalGrowthMeasurement.getHeight();
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

    public BigDecimal getHeight() {
        return height;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}