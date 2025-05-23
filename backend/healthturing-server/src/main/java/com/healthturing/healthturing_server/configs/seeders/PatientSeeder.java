package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.repositories.PatientRepository;
import com.healthturing.healthturing_server.repositories.UserRepository;

@Order(5)
@Component
public class PatientSeeder implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (patientRepository.count() == 0) {
            User user = userRepository.findById(2L).orElse(null);

            if (user != null) {
                Patient patient1 = new Patient("David Larrubia", user);
                Patient patient2 = new Patient("Cristiano Ronaldo", user);

                patientRepository.saveAll(List.of(patient1, patient2));

                System.out.println("Pacientes creados con éxito");
            } else {
                System.out.println("El usuario con ID 2 no existe. PatientSeeder cancelado");
            }
        } else {
            System.out.println("La base de datos ya tiene pacientes. PatientSeeder cancelado");
        }
    }
}
