package com.fu.prenancytracker.repository;

import com.fu.prenancytracker.model.RefreshToken;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(@Size(max = 255) @NotNull String token);

    void deleteByUser_Id(Integer userId);
}