package com.healthturing.healthturing_server.repositories;

import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.VerificationToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
