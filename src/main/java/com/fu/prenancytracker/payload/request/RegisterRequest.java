package com.fu.prenancytracker.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.User}
 */
@Value
public class RegisterRequest implements Serializable {
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    @Size(max = 255)
    String password;
    @NotNull
    @Size(max = 255)
    String fullName;
    String address;
    LocalDate dateOfBirth;

    public RegisterRequest(String email, String password, String fullName, String address, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}