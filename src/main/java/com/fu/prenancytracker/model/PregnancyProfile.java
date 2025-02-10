package com.fu.prenancytracker.model;

import com.fu.prenancytracker.payload.request.UpdatePregnancyProfileRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDate;

@Entity
public class PregnancyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "NickName", nullable = false, length = 100)
    private String nickName;

    @NotNull
    @Column(name = "DueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "ConceptionDate")
    private LocalDate conceptionDate;

    @Column(name = "LastPeriodDate")
    private LocalDate lastPeriodDate;

    @Column(name = "PregnancyWeek")
    private Integer pregnancyWeek;

    @Nationalized
    @Lob
    @Column(name = "Notes")
    private String notes;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @ColumnDefault("'active'")
    @Column(name = "Status", nullable = false, length = 50)
    private String status;

    @NotNull
    @ColumnDefault("sysdatetime()")
    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    public PregnancyProfile(Integer id, String nickName, LocalDate dueDate, LocalDate conceptionDate, LocalDate lastPeriodDate, Integer pregnancyWeek, String notes, String status, Instant createdDate, User user) {
        this.id = id;
        this.nickName = nickName;
        this.dueDate = dueDate;
        this.conceptionDate = conceptionDate;
        this.lastPeriodDate = lastPeriodDate;
        this.pregnancyWeek = pregnancyWeek;
        this.notes = notes;
        this.status = status;
        this.createdDate = createdDate;
        this.user = user;
    }

    public PregnancyProfile() {

    }

    public PregnancyProfile(UpdatePregnancyProfileRequest updatePregnancyProfileRequest) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getConceptionDate() {
        return conceptionDate;
    }

    public void setConceptionDate(LocalDate conceptionDate) {
        this.conceptionDate = conceptionDate;
    }

    public LocalDate getLastPeriodDate() {
        return lastPeriodDate;
    }

    public void setLastPeriodDate(LocalDate lastPeriodDate) {
        this.lastPeriodDate = lastPeriodDate;
    }

    public Integer getPregnancyWeek() {
        return pregnancyWeek;
    }

    public void setPregnancyWeek(Integer pregnancyWeek) {
        this.pregnancyWeek = pregnancyWeek;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}