package com.healthturing.healthturing_server.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.UserAlreadyExistsException;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.UserRepository;
import com.healthturing.healthturing_server.validations.ValidationsFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final ValidationsFunctions validationsFunctions;
    private final PasswordEncoder passwordEncoder;

    public long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        //TODO: cambiar orden de throw adecuadamente
        if (principal instanceof User user) {
            return user.getId();
        }

        throw new SecurityException("Usuario no autenticado correctamente");
    }

    public void deleteUser(long id){

        //TODO: ver si hace todos los deletes on cascade o hacerlos manuales
        
        userRepository.deleteById(id);

    }

    /* public void giveAdminRole(User user){

        user.setRole(Role.ROLE_ADMIN);

        userRepository.save(user);

    } */

    public void createAdmin(String email, String name, String password){

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("Los campos no pueden estar vacíos");
        }

        if (!validationsFunctions.isValidEmail(email)) {
            throw new RuntimeException("El formato de email es incorrecto");
        }

        if (!validationsFunctions.isValidPassword(password)) {
            throw new RuntimeException("La contraseña no cumple con los requisitos de seguridad");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Ya existe un usuario registrado con este email");
        }

        User user = new User(email, name, passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setRole(Role.ROLE_ADMIN);

        userRepository.save(user);

    }
}
