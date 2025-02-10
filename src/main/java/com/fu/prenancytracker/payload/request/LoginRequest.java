package com.fu.prenancytracker.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fu.prenancytracker.model.User}
 */
@Value
public class LoginRequest implements Serializable {
    @NotNull
    String email;

    @NotNull
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}