package com.fu.prenancytracker.model;

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
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "Email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "Password", nullable = false)
    private String password;

    @Size(max = 255)
    @NotNull
    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Nationalized
    @Lob
    @Column(name = "Address")
    private String address;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Size(max = 255)
    @Column(name = "AvatarUrl")
    private String avatarUrl;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "RoleID", nullable = false)
    private Role role;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "EmailVerified", nullable = false)
    private Boolean emailVerified = false;

    @NotNull
    @ColumnDefault("sysdatetime()")
    @Column(name = "CreatedDate", nullable = false)
    private Instant createdDate;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Status", nullable = false, length = 50)
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}