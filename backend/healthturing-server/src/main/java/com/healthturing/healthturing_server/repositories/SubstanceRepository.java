package com.healthturing.healthturing_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.Substance;

public interface SubstanceRepository extends JpaRepository<Substance, Long> {

    Optional<Substance> findByName(String name); 
    
}
