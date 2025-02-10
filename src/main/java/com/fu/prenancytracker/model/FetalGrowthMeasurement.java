package com.fu.prenancytracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "FetalGrowthMeasurements")
public class FetalGrowthMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PregnancyProfileID", nullable = false)
    private PregnancyProfile pregnancyProfile;

    @NotNull
    @Column(name = "WeekNumber", nullable = false)
    private Integer weekNumber;

    @NotNull
    @Column(name = "MeasurementDate", nullable = false)
    private LocalDate measurementDate;

    @NotNull
    @Column(name = "Weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "Height", precision = 5, scale = 2)
    private BigDecimal height;

    @Column(name = "HeadCircumference", precision = 5, scale = 2)
    private BigDecimal headCircumference;

    @Column(name = "BellyCircumference", precision = 5, scale = 2)
    private BigDecimal bellyCircumference;

    @Column(name = "HeartRate")
    private Integer heartRate;

    @Column(name = "MovementCount")
    private Integer movementCount;

    @Nationalized
    @Lob
    @Column(name = "Notes")
    private String notes;

    @NotNull
    @ColumnDefault("sysdatetime()")
    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    public FetalGrowthMeasurement(Integer id, PregnancyProfile pregnancyProfile, Integer weekNumber, LocalDate measurementDate, BigDecimal weight, BigDecimal height, BigDecimal headCircumference, BigDecimal bellyCircumference, Integer heartRate, Integer movementCount, String notes, Instant createdDate) {
        this.id = id;
        this.pregnancyProfile = pregnancyProfile;
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

    public FetalGrowthMeasurement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PregnancyProfile getPregnancyProfile() {
        return pregnancyProfile;
    }

    public void setPregnancyProfile(PregnancyProfile pregnancyProfile) {
        this.pregnancyProfile = pregnancyProfile;
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