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
                        "https://cima.aemps.es/cima/dochtml/p/60627/Prospecto_60627.html"),
                new Medicament(
                        "Simvastatina",
                        "Simvastatinum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "20 mg",
                        "https://cima.aemps.es/cima/dochtml/p/58351/Prospecto_58351.html"),
                new Medicament(
                        "Omeprazol",
                        "Omeprazolum",
                        "Cápsulas gastrorresistentes",
                        "Oral",
                        "20 mg",
                        "https://cima.aemps.es/cima/dochtml/p/63376/Prospecto_63376.html"),
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
                        "https://cima.aemps.es/cima/dochtml/p/58781/Prospecto_58781.html"),
                new Medicament(
                        "Metformina",
                        "Metforminum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "850 mg",
                        "https://cima.aemps.es/cima/dochtml/p/61119/Prospecto_61119.html"),
                new Medicament(
                        "Diazepam",
                        "Diazepamum",
                        "Comprimidos",
                        "Oral",
                        "5 mg",
                        "https://cima.aemps.es/cima/dochtml/p/57434/Prospecto_57434.html"),
                new Medicament(
                        "Atorvastatina",
                        "Atorvastatinum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "40 mg",
                        "https://cima.aemps.es/cima/dochtml/p/65527/Prospecto_65527.html"),
                new Medicament(
                        "Losartán",
                        "Losartanum",
                        "Comprimidos",
                        "Oral",
                        "50 mg",
                        "https://cima.aemps.es/cima/dochtml/p/64578/Prospecto_64578.html"),
                new Medicament(
                        "Levotiroxina",
                        "Levothyroxinum",
                        "Comprimidos",
                        "Oral",
                        "100 µg",
                        "https://cima.aemps.es/cima/dochtml/p/65789/Prospecto_65789.html"),
                new Medicament(
                        "Sertralina",
                        "Sertralinum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "100 mg",
                        "https://cima.aemps.es/cima/dochtml/p/65901/Prospecto_65901.html"),
                new Medicament(
                        "Amlodipino",
                        "Amlodipinum",
                        "Comprimidos",
                        "Oral",
                        "10 mg",
                        "https://cima.aemps.es/cima/dochtml/p/66123/Prospecto_66123.html"),
                new Medicament(
                        "Clopidogrel",
                        "Clopidogrelum",
                        "Comprimidos recubiertos",
                        "Oral",
                        "75 mg",
                        "https://cima.aemps.es/cima/dochtml/p/66345/Prospecto_66345.html"),
                new Medicament(
                        "Metoprolol",
                        "Metoprololum",
                        "Comprimidos de liberación prolongada",
                        "Oral",
                        "100 mg",
                        "https://cima.aemps.es/cima/dochtml/p/66567/Prospecto_66567.html"),
                new Medicament(
                        "Enalapril",
                        "Enalaprilum",
                        "Comprimidos",
                        "Oral",
                        "20 mg",
                        "https://cima.aemps.es/cima/dochtml/p/66789/Prospecto_66789.html"),
                new Medicament(
                        "Furosemida",
                        "Furosemidum",
                        "Comprimidos",
                        "Oral",
                        "40 mg",
                        "https://cima.aemps.es/cima/dochtml/p/67011/Prospecto_67011.html"),
                new Medicament(
                        "Salbutamol",
                        "Salbutamolum",
                        "Inhalador",
                        "Inhalación",
                        "100 µg/dosis",
                        "https://cima.aemps.es/cima/dochtml/p/67233/Prospecto_67233.html")

        );

        for (Medicament medicament : medicaments) {
            if (medicamentRepository.findByScientificName(medicament.getScientificName()).isEmpty()) {
                medicamentRepository.save(medicament);
            }
        }
    }
}