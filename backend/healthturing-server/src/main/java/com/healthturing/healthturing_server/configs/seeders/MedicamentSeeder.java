package com.healthturing.healthturing_server.configs.seeders;

import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class MedicamentSeeder implements CommandLineRunner {

    private final MedicamentRepository medicamentRepository;

    public MedicamentSeeder(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Medicament> medicaments = List.of(
                new Medicament(
                        "Paracetamol",
                        "Paracetamol",
                        "Comprimidos",
                        "Oral",
                        "1 g",
                        "https://cima.aemps.es/cima/dochtml/p/70310/P_70310.html"),
                new Medicament(
                        "Ibuprofeno",
                        "Ibuprofenum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "600 mg",
                        "https://cima.aemps.es/cima/dochtml/p/65251/P_65251.html"),
                new Medicament(
                        "Simvastatina",
                        "Simvastatinum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "20 mg",
                        "https://cima.aemps.es/cima/dochtml/p/65198/P_65198.html"),
                new Medicament(
                        "Omeprazol",
                        "Omeprazolum",
                        "Cápsulas gastrorresistentes",
                        "Oral",
                        "20 mg",
                        "https://cima.aemps.es/cima/dochtml/p/81392/P_81392.html"),
                new Medicament(
                        "Amoxicilina",
                        "Amoxicillinum",
                        "Cápsulas",
                        "Oral",
                        "500 mg",
                        "https://cima.aemps.es/cima/dochtml/p/62586/P_62586.html"),
                new Medicament(
                        "Loratadina",
                        "Loratadinum",
                        "Comprimidos",
                        "Oral",
                        "10 mg",
                        "https://cima.aemps.es/cima/dochtml/p/63784/P_63784.html"),
                new Medicament(
                        "Metformina",
                        "Metforminum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "850 mg",
                        "https://cima.aemps.es/cima/dochtml/p/88810/P_88810.html"),
                new Medicament(
                        "Diazepam",
                        "Diazepamum",
                        "Comprimidos",
                        "Oral",
                        "5 mg",
                        "https://cima.aemps.es/cima/dochtml/p/80698/P_80698.html"),
                new Medicament(
                        "Atorvastatina",
                        "Atorvastatinum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "40 mg",
                        "https://cima.aemps.es/cima/dochtml/p/69537/P_69537.html"),
                new Medicament(
                        "Losartán",
                        "Losartanum",
                        "Comprimidos",
                        "Oral",
                        "50 mg",
                        "https://cima.aemps.es/cima/dochtml/p/67912/P_67912.html"),
                new Medicament(
                        "Levotiroxina",
                        "Levothyroxinum",
                        "Comprimidos",
                        "Oral",
                        "100 µg",
                        "https://cima.aemps.es/cima/dochtml/p/77802/P_77802.html"),
                new Medicament(
                        "Sertralina",
                        "Sertralinum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "100 mg",
                        "https://cima.aemps.es/cima/dochtml/p/66058/P_66058.html"),
                new Medicament(
                        "Amlodipino",
                        "Amlodipinum",
                        "Comprimidos",
                        "Oral",
                        "10 mg",
                        "https://cima.aemps.es/cima/dochtml/p/65461/P_65461.html"),
                new Medicament(
                        "Salbutamol",
                        "Salbutamolum",
                        "Inhalador",
                        "Inhalación",
                        "100 µg/dosis",
                        "https://cima.aemps.es/cima/dochtml/p/65850/P_65850.html")

        );

        for (Medicament medicament : medicaments) {
            if (medicamentRepository.findByScientificName(medicament.getScientificName()).isEmpty()) {
                medicamentRepository.save(medicament);
            }
        }
    }
}