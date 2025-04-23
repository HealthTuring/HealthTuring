package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.RoleEnum;
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
            User admin = new User("admin@mail.com" , "adminName", "administrator", passwordEncoder.encode("adminpass"));
            //admin.setRole(Role.Admin);

            User user = new User("user@mail.com" , "userName", "userlastname", passwordEncoder.encode("userpass"));

            User doctor = new User("doc@mail.com", "doctor", "doctorLN", passwordEncoder.encode("docpass"));

            userRepository.saveAll(List.of(admin, user));

            System.out.println("Usuarios creados con éxito");
        }else{
            System.out.println("La base de datos ya tiene usuarios. UserSeeder cancelado");
        }
    }
}
