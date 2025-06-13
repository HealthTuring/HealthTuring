package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.Role;
import com.healthturing.healthturing_server.repositories.UserRepository;

/**
 * Seeder de la entidad User
 * Crea los usuarios admin, user, y doc, cada uno con su rol correspondiente. En caso de ya existir datos se omite el seeder
 * Requiere del repositorio de Usuario para crearlos, BCryptPasswordEncoder para encriptar las contraseñas
 */
@Order(1)
@Component
public class UserSeeder implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    
    private BCryptPasswordEncoder passwordEncoder;

    public UserSeeder(){
        this.passwordEncoder=new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args){
        if(userRepository.count() == 0){
            User admin = new User("admin@mail.com" , "SuperAdmin", passwordEncoder.encode("$Adminpass1"));
            admin.setRole(Role.ROLE_ADMIN);
            admin.setEnabled(true);

            User user = new User("user@mail.com" , "Rafael Nadal", passwordEncoder.encode("$Userpass1"));
            user.setEnabled(true);

            User user2 = new User("user2@mail.com" , "Novak Djokovic", passwordEncoder.encode("$Userpass1"));
            user2.setEnabled(true);

            User doctor = new User("doc@mail.com", "Paco Datos", passwordEncoder.encode("$Docpass1"));
            doctor.setRole(Role.ROLE_DOC);
            doctor.setEnabled(true);

            User doctor2 = new User("doc2@mail.com", "Manolo Nodos", passwordEncoder.encode("$Docpass1"));
            doctor2.setRole(Role.ROLE_DOC);
            doctor2.setEnabled(true);

            userRepository.saveAll(List.of(admin, user, user2, doctor, doctor2));

            System.out.println("Usuarios creados con éxito");
        }else{
            System.out.println("La base de datos ya tiene usuarios. UserSeeder cancelado");
        }
    }
}
