package com.healthturing.healthturing_server.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    
}
