package com.fu.prenancytracker.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginResponse {
    private Integer userID;
    private String email;
    private String accountPhoto;
    private String role;
    private String refreshToken;
    private String accessToken;

    public LoginResponse() {
    }

    public LoginResponse(Integer userID, String email, String accountPhoto, String role, String refreshToken, String accessToken) {
        this.userID = userID;
        this.email = email;
        this.accountPhoto = accountPhoto;
        this.role = role;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountPhoto() {
        return accountPhoto;
    }

    public void setAccountPhoto(String accountPhoto) {
        this.accountPhoto = accountPhoto;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
