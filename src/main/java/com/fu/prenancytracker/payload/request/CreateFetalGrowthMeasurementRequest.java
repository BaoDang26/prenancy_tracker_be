package com.fu.prenancytracker.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.FetalGrowthMeasurement}
 */
@Value
public class CreateFetalGrowthMeasurementRequest implements Serializable {
    Integer pregnancyProfileId;
    @NotNull(message = "Week number is a large integer greater than 0 and less than 40.")
    @Min(message = "Week number is a large integer greater than 0 ", value = 0)
    @Max(message = "Week number is a large integer less than 40", value = 40)
    Integer weekNumber;
    @NotNull
    LocalDate measurementDate;
    @NotNull
    BigDecimal weight;
    BigDecimal height;
    BigDecimal headCircumference;
    BigDecimal bellyCircumference;
    Integer heartRate;
    Integer movementCount;
    String notes;

    public Integer getPregnancyProfileId() {
        return pregnancyProfileId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getHeadCircumference() {
        return headCircumference;
    }

    public BigDecimal getBellyCircumference() {
        return bellyCircumference;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Integer getMovementCount() {
        return movementCount;
    }

    public String getNotes() {
        return notes;
    }
}