package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.FetalGrowthMeasurement;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.FetalGrowthMeasurement}
 */

public class FetalGrowthMeasurementResponse implements Serializable {
    Integer id;
    Integer pregnancyProfileId;
    Integer weekNumber;
    LocalDate measurementDate;
    BigDecimal weight;
    BigDecimal height;
    BigDecimal headCircumference;
    BigDecimal bellyCircumference;
    Integer heartRate;
    Integer movementCount;
    String notes;
    Instant createdDate;

    public FetalGrowthMeasurementResponse(Integer id, Integer pregnancyProfileId, Integer weekNumber, LocalDate measurementDate, BigDecimal weight, BigDecimal height, BigDecimal headCircumference, BigDecimal bellyCircumference, Integer heartRate, Integer movementCount, String notes, Instant createdDate) {
        this.id = id;
        this.pregnancyProfileId = pregnancyProfileId;
        this.weekNumber = weekNumber;
        this.measurementDate = measurementDate;
        this.weight = weight;
        this.height = height;
        this.headCircumference = headCircumference;
        this.bellyCircumference = bellyCircumference;
        this.heartRate = heartRate;
        this.movementCount = movementCount;
        this.notes = notes;
        this.createdDate = createdDate;
    }

    public FetalGrowthMeasurementResponse(FetalGrowthMeasurement createdMeasurement) {
        this.id = createdMeasurement.getId();
        this.pregnancyProfileId = createdMeasurement.getPregnancyProfile().getId();
        this.weekNumber = createdMeasurement.getWeekNumber();
        this.measurementDate = createdMeasurement.getMeasurementDate();
        this.weight = createdMeasurement.getWeight();
        this.height = createdMeasurement.getHeight();
        this.headCircumference = createdMeasurement.getHeadCircumference();
        this.bellyCircumference = createdMeasurement.getBellyCircumference();
        this.heartRate = createdMeasurement.getHeartRate();
        this.movementCount = createdMeasurement.getMovementCount();
        this.notes = createdMeasurement.getNotes();
        this.createdDate = createdMeasurement.getCreatedDate();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPregnancyProfileId() {
        return pregnancyProfileId;
    }

    public void setPregnancyProfileId(Integer pregnancyProfileId) {
        this.pregnancyProfileId = pregnancyProfileId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(LocalDate measurementDate) {
        this.measurementDate = measurementDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(BigDecimal headCircumference) {
        this.headCircumference = headCircumference;
    }

    public BigDecimal getBellyCircumference() {
        return bellyCircumference;
    }

    public void setBellyCircumference(BigDecimal bellyCircumference) {
        this.bellyCircumference = bellyCircumference;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getMovementCount() {
        return movementCount;
    }

    public void setMovementCount(Integer movementCount) {
        this.movementCount = movementCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}