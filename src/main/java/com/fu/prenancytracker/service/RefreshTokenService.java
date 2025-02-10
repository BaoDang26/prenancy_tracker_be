package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService extends GeneralService<RefreshToken> {
    Optional<RefreshToken> findByToken(String token);

    int deleteByUserId(Long userId);

    RefreshToken createRefreshToken(Integer userID);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUserID(Integer userID);
}
