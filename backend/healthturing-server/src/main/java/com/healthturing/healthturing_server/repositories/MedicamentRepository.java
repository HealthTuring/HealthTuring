package com.healthturing.healthturing_server.repositories;

import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.Medicament;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long>{
    Optional<Medicament> findByCommonName(String commonName);
}
