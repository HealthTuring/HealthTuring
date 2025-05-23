package com.healthturing.healthturing_server.configs.seeders;

import com.healthturing.healthturing_server.models.Substance;
import com.healthturing.healthturing_server.repositories.SubstanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Order(2)
public class SubstanceSeeder implements CommandLineRunner {

    private final SubstanceRepository substanceRepository;

    public SubstanceSeeder(SubstanceRepository substanceRepository) {
        this.substanceRepository = substanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> substanceNames = List.of(
                "Alcohol",
                "Cafeína",
                "Vitamina K",
                "Vitamina C",
                "Hierba de San Juan",
                "Pomelo",
                "Tabaco",
                "Lácteos",
                "Ajo",
                "Alimentos ricos en potasio",
                "Antiácidos",
                "Salicilatos",
                "Alimentos ricos en calcio",
                "Regaliz",
                "Alimentos ricos en fibra");

        for (String name : substanceNames) {
            if (substanceRepository.findByName(name).isEmpty()) {
                substanceRepository.save(new Substance(name));
            }
        }
    }

}