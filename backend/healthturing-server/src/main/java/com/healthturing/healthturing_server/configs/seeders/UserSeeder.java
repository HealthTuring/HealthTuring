package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.UserRepository;

@Component
public class UserSeeder implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    
    private BCryptPasswordEncoder passwordEncoder;

    public UserSeeder(){
        this.passwordEncoder=new BCryptPasswordEncoder();
    }

    //BCryptPassword encoder

    @Override
    public void run(String... args){
        if(userRepository.count() == 0){
            User admin = new User("admin@mail.com" , "adminName", passwordEncoder.encode("adminpass"));
            admin.setRole(Role.ROLE_ADMIN);
            admin.setEnabled(true);

            User user = new User("user@mail.com" , "userName", passwordEncoder.encode("userpass"));
            user.setEnabled(true);

            User doctor = new User("doc@mail.com", "doctor", passwordEncoder.encode("docpass"));
            doctor.setRole(Role.ROLE_DOC);
            doctor.setEnabled(true);

            userRepository.saveAll(List.of(admin, user, doctor));

            System.out.println("Usuarios creados con éxito");
        }else{
            System.out.println("La base de datos ya tiene usuarios. UserSeeder cancelado");
        }
    }
}
