package com.healthturing.healthturing_server.configs.seeders;

import com.healthturing.healthturing_server.models.Incompatibility;
import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.models.Substance;
import com.healthturing.healthturing_server.repositories.IncompatibilityRepository;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;
import com.healthturing.healthturing_server.repositories.SubstanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(4)
public class IncompatibilitySeeder implements CommandLineRunner {

    private final IncompatibilityRepository incompatibilityRepository;
    private final MedicamentRepository medicamentRepository;
    private final SubstanceRepository substanceRepository;

    public IncompatibilitySeeder(IncompatibilityRepository incompatibilityRepository,
                                 MedicamentRepository medicamentRepository,
                                 SubstanceRepository substanceRepository) {
        this.incompatibilityRepository = incompatibilityRepository;
        this.medicamentRepository = medicamentRepository;
        this.substanceRepository = substanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo ejecuta si no hay datos
        if (incompatibilityRepository.count() > 0) return;

        // Obtén todos los medicamentos y sustancias
        Map<String, Medicament> meds = new HashMap<>();
        medicamentRepository.findAll().forEach(m -> meds.put(m.getCommonName().toLowerCase(), m));

        Map<String, Substance> subs = new HashMap<>();
        substanceRepository.findAll().forEach(s -> subs.put(s.getName().toLowerCase(), s));

        List<Incompatibility> incompatibilities = new ArrayList<>();

        // Ejemplos reales y plausibles de incompatibilidades

        // Alcohol
        incompatibilities.add(new Incompatibility(meds.get("paracetamol"), null, subs.get("alcohol")));
        incompatibilities.add(new Incompatibility(meds.get("diazepam"), null, subs.get("alcohol")));
        incompatibilities.add(new Incompatibility(meds.get("sertralina"), null, subs.get("alcohol")));
        incompatibilities.add(new Incompatibility(meds.get("metformina"), null, subs.get("alcohol")));
        incompatibilities.add(new Incompatibility(meds.get("ibuprofeno"), null, subs.get("alcohol")));
        incompatibilities.add(new Incompatibility(meds.get("loratadina"), null, subs.get("alcohol")));

        // Cafeína
        incompatibilities.add(new Incompatibility(meds.get("metoprolol"), null, subs.get("cafeína")));
        incompatibilities.add(new Incompatibility(meds.get("sertralina"), null, subs.get("cafeína")));

        // Vitamina K (antagoniza anticoagulantes como Clopidogrel)
        incompatibilities.add(new Incompatibility(meds.get("clopidogrel"), null, subs.get("vitamina k")));

        // Pomelo (afecta metabolismo de estatinas)
        incompatibilities.add(new Incompatibility(meds.get("simvastatina"), null, subs.get("pomelo")));
        incompatibilities.add(new Incompatibility(meds.get("atorvastatina"), null, subs.get("pomelo")));

        // Hierba de San Juan (inductor enzimático, reduce eficacia de varios medicamentos)
        incompatibilities.add(new Incompatibility(meds.get("sertralina"), null, subs.get("hierba de san juan")));
        incompatibilities.add(new Incompatibility(meds.get("diazepam"), null, subs.get("hierba de san juan")));
        incompatibilities.add(new Incompatibility(meds.get("amoxicilina"), null, subs.get("hierba de san juan")));

        // Tabaco (afecta a betabloqueantes y antidepresivos)
        incompatibilities.add(new Incompatibility(meds.get("metoprolol"), null, subs.get("tabaco")));
        incompatibilities.add(new Incompatibility(meds.get("sertralina"), null, subs.get("tabaco")));

        // Lácteos (pueden reducir absorción de amoxicilina)
        incompatibilities.add(new Incompatibility(meds.get("amoxicilina"), null, subs.get("lácteos")));

        // Ajo (potencia efecto de anticoagulantes)
        incompatibilities.add(new Incompatibility(meds.get("clopidogrel"), null, subs.get("ajo")));

        // Alimentos ricos en potasio (peligro con IECA y diuréticos)
        incompatibilities.add(new Incompatibility(meds.get("enalapril"), null, subs.get("alimentos ricos en potasio")));
        incompatibilities.add(new Incompatibility(meds.get("furosemida"), null, subs.get("alimentos ricos en potasio")));

        // Antiácidos (alteran absorción de levotiroxina, omeprazol)
        incompatibilities.add(new Incompatibility(meds.get("levotiroxina"), null, subs.get("antiácidos")));
        incompatibilities.add(new Incompatibility(meds.get("omeprazol"), null, subs.get("antiácidos")));

        // Salicilatos (potencian efecto de furosemida)
        incompatibilities.add(new Incompatibility(meds.get("furosemida"), null, subs.get("salicilatos")));

        // Alimentos ricos en calcio (afectan absorción de levotiroxina)
        incompatibilities.add(new Incompatibility(meds.get("levotiroxina"), null, subs.get("alimentos ricos en calcio")));

        // Regaliz (puede aumentar presión arterial, cuidado con antihipertensivos)
        incompatibilities.add(new Incompatibility(meds.get("amlodipino"), null, subs.get("regaliz")));
        incompatibilities.add(new Incompatibility(meds.get("enalapril"), null, subs.get("regaliz")));

        // Alimentos ricos en fibra (afectan absorción de levotiroxina)
        incompatibilities.add(new Incompatibility(meds.get("levotiroxina"), null, subs.get("alimentos ricos en fibra")));

        // Incompatibilidades medicamento-medicamento (ejemplos)
        incompatibilities.add(new Incompatibility(meds.get("ibuprofeno"), meds.get("enalapril"), null));
        incompatibilities.add(new Incompatibility(meds.get("ibuprofeno"), meds.get("losartán"), null));
        incompatibilities.add(new Incompatibility(meds.get("enalapril"), meds.get("furosemida"), null));
        incompatibilities.add(new Incompatibility(meds.get("metformina"), meds.get("furosemida"), null));
        incompatibilities.add(new Incompatibility(meds.get("clopidogrel"), meds.get("omeprazol"), null));
        incompatibilities.add(new Incompatibility(meds.get("sertralina"), meds.get("diazepam"), null));
        incompatibilities.add(new Incompatibility(meds.get("atorvastatina"), meds.get("amoxicilina"), null));
        incompatibilities.add(new Incompatibility(meds.get("simvastatina"), meds.get("amoxicilina"), null));
        incompatibilities.add(new Incompatibility(meds.get("amlodipino"), meds.get("losartán"), null));
        incompatibilities.add(new Incompatibility(meds.get("paracetamol"), meds.get("ibuprofeno"), null));
        incompatibilities.add(new Incompatibility(meds.get("amoxicilina"), meds.get("omeprazol"), null));
        incompatibilities.add(new Incompatibility(meds.get("paracetamol"), meds.get("sertralina"), null));

        incompatibilityRepository.saveAll(incompatibilities);
        System.out.println("Seeder de incompatibilidades ejecutado correctamente.");
    }
}