package com.healthturing.healthturing_server.configs.seeders;

import com.healthturing.healthturing_server.models.Appointment;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.repositories.AppointmentRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@Order(7)
public class AppointmentSeeder implements CommandLineRunner {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(String... args) {
        if (appointmentRepository.count() > 0) {
            return;
        }

        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) {
            System.err.println("No patients found. Please seed patients before seeding appointments.");
            return;
        }

        Appointment a1 = new Appointment(
                LocalTime.of(9, 0),
                LocalTime.of(9, 30),
                LocalDate.now().plusDays(1),
                patients.get(0),
                "Consulta general"
        );

        Appointment a2 = new Appointment(
                LocalTime.of(10, 0),
                LocalTime.of(10, 30),
                LocalDate.now().plusDays(2),
                patients.get(Math.min(1, patients.size() - 1)),
                "Chequeo anual"
        );

        Appointment a3 = new Appointment(
                LocalTime.of(14, 0),
                LocalTime.of(14, 30),
                LocalDate.now().plusDays(3),
                patients.get(0),
                "Revisión de resultados"
        );

        appointmentRepository.saveAll(List.of(a1, a2, a3));
        System.out.println("Seeder de cita con éxito");
    }
}