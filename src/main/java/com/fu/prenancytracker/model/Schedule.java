package com.fu.prenancytracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDate;


@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "Title", nullable = false, length = 20)
    private String title;

    @NotNull
    @Nationalized
    @Lob
    @Column(name = "Description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "EventDate", nullable = false)
    private LocalDate eventDate;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Status", nullable = false, length = 50)
    private String status;

    @NotNull
    @Column(name = "IsCompleted", nullable = false)
    private Boolean isCompleted = false;

    @NotNull
    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PregncacyID", nullable = false)
    private PregnancyProfile pregncacyID;

    public Schedule(Integer id, String title, String description, LocalDate eventDate, String status, Boolean isCompleted, Instant createdDate, User user, PregnancyProfile pregncacyID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.status = status;
        this.isCompleted = isCompleted;
        this.createdDate = createdDate;
        this.user = user;
        this.pregncacyID = pregncacyID;
    }

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
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

    public PregnancyProfile getPregncacyID() {
        return pregncacyID;
    }

    public void setPregncacyID(PregnancyProfile pregncacyID) {
        this.pregncacyID = pregncacyID;
    }
}