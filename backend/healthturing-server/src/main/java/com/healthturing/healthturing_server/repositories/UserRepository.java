package com.healthturing.healthturing_server.repositories;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.VerificationToken;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
    Optional<User> findByVerificationToken(VerificationToken verificationToken);
    
}
