package com.healthturing.healthturing_server.configs.seeders;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.User;
import com.healthturing.healthturing_server.models.enums.BloodGroup;
import com.healthturing.healthturing_server.models.enums.Gender;
import com.healthturing.healthturing_server.models.enums.RhFactor;
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
            User user2 = userRepository.findById(3L).orElse(null);
            User doctor = userRepository.findById(4L).orElse(null);
            User doctor2 = userRepository.findById(5L).orElse(null);

            if (user != null && doctor != null && doctor2 != null && user2 != null) {
                Patient patient1 = new Patient(
                    "David Larrubia",
                    "12345678A",
                    LocalDate.of(1995, 1, 15),
                    Gender.M,
                    BloodGroup.O,
                    RhFactor.POSITIVE,
                    "Juan Larrubia - 600123456",
                    user,
                    doctor
                );

                Patient patient2 = new Patient(
                    "Cristiano Ronaldo",
                    "98765432B",
                    LocalDate.of(1985, 2, 5),
                    Gender.M,
                    BloodGroup.A,
                    RhFactor.POSITIVE,
                    "Dolores Aveiro - 600654321",
                    user2,
                    doctor
                );

                Patient patient3 = new Patient(
                    "Rafael Nadal",
                    "45678901C",
                    LocalDate.of(1986, 6, 3),
                    Gender.M,
                    BloodGroup.B,
                    RhFactor.NEGATIVE,
                    "Ana María Parera - 600789012",
                    user,
                    doctor2
                );

                patientRepository.saveAll(List.of(patient1, patient2, patient3));

                System.out.println("Pacientes creados con éxito");
            } else {
                System.out.println("Uno de los usuarios/doctores no existe. PatientSeeder cancelado");
            }
        } else {
            System.out.println("La base de datos ya tiene pacientes. PatientSeeder cancelado");
        }
    }
}