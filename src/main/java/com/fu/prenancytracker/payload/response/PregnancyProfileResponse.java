package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.PregnancyProfile;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.PregnancyProfile}
 */
@Value
public class PregnancyProfileResponse implements Serializable {
    Integer id;
    String nickName;
    LocalDate dueDate;
    LocalDate conceptionDate;
    LocalDate lastPeriodDate;
    Integer pregnancyWeek;
    String notes;
    String status;
    Instant createdDate;
    Integer userID;

    public PregnancyProfileResponse(Integer id, String nickName, LocalDate dueDate, LocalDate conceptionDate, LocalDate lastPeriodDate, Integer pregnancyWeek, String notes, String status, Instant createdDate, Integer userID) {
        this.id = id;
        this.nickName = nickName;
        this.dueDate = dueDate;
        this.conceptionDate = conceptionDate;
        this.lastPeriodDate = lastPeriodDate;
        this.pregnancyWeek = pregnancyWeek;
        this.notes = notes;
        this.status = status;
        this.createdDate = createdDate;
        this.userID = userID;
    }


    public Integer getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getConceptionDate() {
        return conceptionDate;
    }

    public LocalDate getLastPeriodDate() {
        return lastPeriodDate;
    }

    public Integer getPregnancyWeek() {
        return pregnancyWeek;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Integer getUserID() {
        return userID;
    }
}