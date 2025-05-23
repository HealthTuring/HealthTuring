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

        if (patient1Opt.isEmpty() || patient2Opt.isEmpty()) {
            System.out.println("Pacientes de prueba no encontrados, seeder de tratamientos no ejecutado.");
            return;
        }

        Patient patient1 = patient1Opt.get();
        Patient patient2 = patient2Opt.get();

        Medicament paracetamol = medicamentRepository.findByScientificName("Paracetamol").orElse(null);
        Medicament amoxicilina = medicamentRepository.findByScientificName("Amoxicillinum").orElse(null);
        Medicament ibuprofeno = medicamentRepository.findByScientificName("Ibuprofenum").orElse(null);
        Medicament losartan = medicamentRepository.findByScientificName("Losartanum").orElse(null);

        List<Treatment> treatments = new ArrayList<>();

        if (paracetamol != null) {
            treatments.add(new Treatment(
                    "Tratamiento de fiebre",
                    "Paracetamol 500mg para bajar la fiebre",
                    LocalDate.now().minusDays(3),
                    LocalDate.now().plusDays(2),
                    "7 días",
                    "1 cada 8 horas",
                    patient1,
                    paracetamol
            ));
        }
        if (amoxicilina != null) {
            treatments.add(new Treatment(
                    "Infección respiratoria",
                    "Amoxicilina 500mg cada 8 horas",
                    LocalDate.now().minusDays(1),
                    LocalDate.now().plusDays(6),
                    "7 días",
                    "1 cada 8 horas",
                    patient1,
                    amoxicilina
            ));
        }

        if (ibuprofeno != null) {
            treatments.add(new Treatment(
                    "Dolor de cabeza",
                    "Ibuprofeno 400mg cada 12 horas",
                    LocalDate.now(),
                    LocalDate.now().plusDays(4),
                    "5 días",
                    "1 cada 12 horas",
                    patient2,
                    ibuprofeno
            ));
        }
        if (losartan != null) {
            treatments.add(new Treatment(
                    "Hipertensión arterial",
                    "Losartán 50mg una vez al día",
                    LocalDate.now().minusDays(10),
                    LocalDate.now().plusDays(20),
                    "30 días",
                    "1 vez al día",
                    patient2,
                    losartan
            ));
        }

        treatmentRepository.saveAll(treatments);
        System.out.println("Seeder de tratamientos ejecutado correctamente.");
    }
}