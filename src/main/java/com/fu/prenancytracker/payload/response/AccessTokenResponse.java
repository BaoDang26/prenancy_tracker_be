package com.fu.prenancytracker.payload.response;

import java.util.Date;

public class AccessTokenResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Date expiredAt;

    public AccessTokenResponse(String accessToken, Date expiredAt) {
        this.accessToken = accessToken;
        this.expiredAt = expiredAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
