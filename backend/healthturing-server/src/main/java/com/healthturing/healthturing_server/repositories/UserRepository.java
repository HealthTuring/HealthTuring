package com.healthturing.healthturing_server.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.VerificationToken;
import com.healthturing.healthturing_server.models.enums.Role;

/**
 * Repostiorio de la entidad User
 * Define las querys necesarias para el manejo de la entidad User
 * Implementa CrudRepository que permite acceso a algunas de las querys rápidamente como save, delete...
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
    Optional<User> findByVerificationToken(VerificationToken verificationToken);

    boolean existsByEmail(String email);

    List<User> findByRole(Role roleDoc);
    
}
