package com.healthturing.healthturing_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthturing.healthturing_server.models.DoctorRegistrationRequest;

public interface DoctorRegistrationRequestRepository extends JpaRepository<DoctorRegistrationRequest, Long> {
    List<DoctorRegistrationRequest> findByApprovedFalse();
}