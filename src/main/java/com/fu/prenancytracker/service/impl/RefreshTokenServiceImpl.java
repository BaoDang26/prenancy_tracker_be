package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.exception.TokenException;
import com.fu.prenancytracker.model.RefreshToken;
import com.fu.prenancytracker.repository.RefreshTokenRepository;
import com.fu.prenancytracker.repository.UserRepository;
import com.fu.prenancytracker.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${pregnancy.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public int deleteByUserId(Long userId) {
        return 0;
    }

    @Override
    public RefreshToken createRefreshToken(Integer userID) {
        RefreshToken refreshToken = new RefreshToken();
        userRepository.findById(userID).ifPresent(refreshToken::setUser);
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setExpiredAt(Instant.now().plusSeconds(refreshTokenDurationMs / 1000));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiredAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenException(
                    token.getToken(),
                    "Refresh token was expired. Please make a new login request");
        }

        return token;
    }

    @Override
    @Transactional
    public void deleteByUserID(Integer userID) {
        refreshTokenRepository.deleteByUser_Id(userID);
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Iterable<RefreshToken> findAll() {
        return refreshTokenRepository.findAll();
    }
}
