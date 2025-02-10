package com.fu.prenancytracker.payload.response;

import com.fu.prenancytracker.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.fu.prenancytracker.model.User}
 */
@Value
public class UserResponse implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    @Size(max = 255)
    String fullName;
    String address;
    LocalDate dateOfBirth;
    String roleRoleName;
    @NotNull
    @Size(max = 50)
    String status;

    public UserResponse(Integer id, String email, String fullName, String address, LocalDate dateOfBirth, String roleRoleName, String status) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.roleRoleName = roleRoleName;
        this.status = status;
    }

    public UserResponse(User userCreated) {
        this.id = userCreated.getId();
        this.email = userCreated.getEmail();
        this.fullName = userCreated.getFullName();
        this.address = userCreated.getAddress();
        this.dateOfBirth = userCreated.getDateOfBirth();
        this.roleRoleName = userCreated.getRole().getRoleName();
        this.status = userCreated.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

    public String getRoleRoleName() {
        return roleRoleName;
    }

    public String getStatus() {
        return status;
    }
}