package com.fu.prenancytracker.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.PregnancyProfile}
 */
@Value
public class CreatePregnancyProfileRequest implements Serializable {
    @NotNull
    @Size(max = 100)
    String nickName;
    @NotNull
    LocalDate dueDate;
    LocalDate conceptionDate;
    LocalDate lastPeriodDate;
    Integer pregnancyWeek;
    String notes;
//    @NotNull
//    @Size(max = 50)
//    String status;

    public CreatePregnancyProfileRequest(String nickName, LocalDate dueDate,
                                         LocalDate conceptionDate, LocalDate lastPeriodDate,
                                         Integer pregnancyWeek, String notes/*, String status*/) {
        this.nickName = nickName;
        this.dueDate = dueDate;
        this.conceptionDate = conceptionDate;
        this.lastPeriodDate = lastPeriodDate;
        this.pregnancyWeek = pregnancyWeek;
        this.notes = notes;
//        this.status = status;
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
//
//    public String getStatus() {
//        return status;
//    }
}