package com.healthturing.healthturing_server.repositories;

import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.VerificationToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    @Query("SELECT t FROM VerificationToken t WHERE t.token=:token")
    Optional<VerificationToken> findvalidar(String token);

}
