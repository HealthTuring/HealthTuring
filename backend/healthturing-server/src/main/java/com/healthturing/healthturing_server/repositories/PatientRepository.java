package com.healthturing.healthturing_server.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAll();

    List<Patient> findByUserId(Long userId);
    List<Patient> findByDoctorId(Long doctorId);
    
}
