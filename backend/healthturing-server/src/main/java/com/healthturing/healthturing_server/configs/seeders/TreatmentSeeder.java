package com.healthturing.healthturing_server.configs.seeders;

import com.healthturing.healthturing_server.models.Treatment;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.repositories.TreatmentRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(6)
public class TreatmentSeeder implements CommandLineRunner {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final MedicamentRepository medicamentRepository;

    public TreatmentSeeder(
            TreatmentRepository treatmentRepository,
            PatientRepository patientRepository,
            MedicamentRepository medicamentRepository) {
        this.treatmentRepository = treatmentRepository;
        this.patientRepository = patientRepository;
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (treatmentRepository.count() > 0) return;

        Optional<Patient> patient1Opt = patientRepository.findById(1L);
        Optional<Patient> patient2Opt = patientRepository.findById(2L);
        Optional<Patient> patient3Opt = patientRepository.findById(3L);

        if (patient1Opt.isEmpty() || patient2Opt.isEmpty()) {
            System.out.println("Pacientes de prueba no encontrados, seeder de tratamientos no ejecutado.");
            return;
        }

        Patient patient1 = patient1Opt.get();
        Patient patient2 = patient2Opt.get(); 
        Patient patient3 = patient3Opt.get(); 

        Medicament paracetamol = medicamentRepository.findByScientificName("Paracetamol").orElse(null);
        Medicament amoxicilina = medicamentRepository.findByScientificName("Amoxicillinum").orElse(null);
        Medicament ibuprofeno = medicamentRepository.findByScientificName("Ibuprofenum").orElse(null);
        Medicament losartan = medicamentRepository.findByScientificName("Losartanum").orElse(null);
        Medicament salbutamol = medicamentRepository.findByScientificName("Salbutamol").orElse(null);

        List<Treatment> treatments = new ArrayList<>();

        if (paracetamol != null) {
            treatments.add(new Treatment(
                    "Tratamiento de fiebre",
                    LocalDate.now().minusDays(3),
                    LocalDate.now().plusDays(2),
                    "7 días",
                    "1 cada 8 horas",
                    patient1,
                    paracetamol
            ));
            treatments.add(new Treatment(
                    "Tratamiento de fiebre",
                    LocalDate.now().minusDays(3),
                    LocalDate.now().plusDays(2),
                    "7 días",
                    "1 cada 8 horas",
                    patient2,
                    paracetamol
            ));
        }
        if (amoxicilina != null) {
            treatments.add(new Treatment(
                    "Infección respiratoria",
                    LocalDate.now().minusDays(1),
                    LocalDate.now().plusDays(6),
                    "7 días",
                    "1 cada 8 horas",
                    patient1,
                    amoxicilina
            ));
            treatments.add(new Treatment(
                    "Infección respiratoria",
                    LocalDate.now().minusDays(1),
                    LocalDate.now().plusDays(6),
                    "7 días",
                    "1 cada 8 horas",
                    patient2,
                    amoxicilina
            ));
        }

        if (ibuprofeno != null) {
            treatments.add(new Treatment(
                    "Dolor de cabeza",
                    LocalDate.now(),
                    LocalDate.now().plusDays(4),
                    "5 días",
                    "1 cada 12 horas",
                    patient2,
                    ibuprofeno
            ));
            treatments.add(new Treatment(
                    "Dolor de cabeza",
                    LocalDate.now(),
                    LocalDate.now().plusDays(4),
                    "5 días",
                    "1 cada 12 horas",
                    patient2,
                    ibuprofeno
            ));
            treatments.add(new Treatment(
                    "Inflamación tendinosa",
                    LocalDate.now(),
                    LocalDate.now().plusDays(3),
                    "3 días",
                    "1 cada 8 horas",
                    patient2,
                    ibuprofeno
            ));
        }
        if (losartan != null) {
            treatments.add(new Treatment(
                    "Hipertensión arterial",
                    LocalDate.now().minusDays(10),
                    LocalDate.now().plusDays(20),
                    "30 días",
                    "1 vez al día",
                    patient2,
                    losartan
            ));
            treatments.add(new Treatment(
                    "Hipertensión arterial",
                    LocalDate.now().minusDays(10),
                    LocalDate.now().plusDays(20),
                    "30 días",
                    "1 vez al día",
                    patient3,
                    losartan
            ));
        }

        if (salbutamol != null) {
            treatments.add(new Treatment(
                "Asma bronquial",
                LocalDate.now().minusDays(7),
                null,
                "Indefinido",
                "1 vez al día",
                patient2,
                salbutamol
            ));
        }

        treatmentRepository.saveAll(treatments);
        System.out.println("Seeder de tratamientos ejecutado correctamente.");
    }
}