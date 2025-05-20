package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.Incompatibility;
import com.healthturing.healthturing_server.repositories.IncompatibilityRepository;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;

@Order(5)
@Component
public class IncompatibilitySeeder implements CommandLineRunner{
    private final MedicamentRepository medicamentRepository;
    private final IncompatibilityRepository incompatibilityRepository;

    public IncompatibilitySeeder(MedicamentRepository medicamentRepository, IncompatibilityRepository incompatibilityRepository){
        this.incompatibilityRepository = incompatibilityRepository;
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public void run(String... args){
        System.out.println("Creando incompatibilidades iniciales entre medicamentos");

        if(incompatibilityRepository.count() == 0){

            List<Incompatibility> incompatibilities = List.of(
                new Incompatibility(medicamentRepository.findByCommonName("Warfarina").get(), medicamentRepository.findByCommonName("Aspirina").get(), 5),
                new Incompatibility(medicamentRepository.findByCommonName("Warfarina").get(), medicamentRepository.findByCommonName("Ibuprofeno").get(), 5),
                new Incompatibility(medicamentRepository.findByCommonName("Warfarina").get(), medicamentRepository.findByCommonName("Clopidogrel").get(), 5),
                new Incompatibility(medicamentRepository.findByCommonName("Diazepam").get(), medicamentRepository.findByCommonName("Alcohol").get(), 5),
                new Incompatibility(medicamentRepository.findByCommonName("Tramadol").get(), medicamentRepository.findByCommonName("Sertralina").get(), 5),
                new Incompatibility(medicamentRepository.findByCommonName("Digoxina").get(), medicamentRepository.findByCommonName("Furosemida").get(), 4),
                new Incompatibility(medicamentRepository.findByCommonName("Levotiroxina").get(), medicamentRepository.findByCommonName("Calcio").get(), 4),
                new Incompatibility(medicamentRepository.findByCommonName("Enalapril").get(), medicamentRepository.findByCommonName("Espironolactona").get(), 4),
                new Incompatibility(medicamentRepository.findByCommonName("Metformina").get(), medicamentRepository.findByCommonName("Alcohol").get(), 4),
                new Incompatibility(medicamentRepository.findByCommonName("Fluoxetina").get(), medicamentRepository.findByCommonName("Tramadol").get(), 4),
                new Incompatibility(medicamentRepository.findByCommonName("Paracetamol").get(), medicamentRepository.findByCommonName("Carbamazepina").get(), 3),
                new Incompatibility(medicamentRepository.findByCommonName("Metoprolol").get(), medicamentRepository.findByCommonName("Verapamilo").get(), 3),
                new Incompatibility(medicamentRepository.findByCommonName("Insulina").get(), medicamentRepository.findByCommonName("Metformina").get(), 3),
                new Incompatibility(medicamentRepository.findByCommonName("Amoxicilina").get(), medicamentRepository.findByCommonName("Metotrexato").get(), 3),
                new Incompatibility(medicamentRepository.findByCommonName("Ranitidina").get(), medicamentRepository.findByCommonName("Ketoconazol").get(), 3),
                new Incompatibility(medicamentRepository.findByCommonName("Aspirina").get(), medicamentRepository.findByCommonName("Ibuprofeno").get(), 2),
                new Incompatibility(medicamentRepository.findByCommonName("Omeprazol").get(), medicamentRepository.findByCommonName("Clopidogrel").get(), 2),
                new Incompatibility(medicamentRepository.findByCommonName("Diazepam").get(), medicamentRepository.findByCommonName("Omeprazol").get(), 2),
                new Incompatibility(medicamentRepository.findByCommonName("Losartán").get(), medicamentRepository.findByCommonName("AINEs").get(), 2),
                new Incompatibility(medicamentRepository.findByCommonName("Metformina").get(), medicamentRepository.findByCommonName("Hidroclorotiazida").get(), 2),
                new Incompatibility(medicamentRepository.findByCommonName("Loratadina").get(), medicamentRepository.findByCommonName("Cetirizina").get(), 1),
                new Incompatibility(medicamentRepository.findByCommonName("Esomeprazol").get(), medicamentRepository.findByCommonName("Ranitidina").get(), 1),
                new Incompatibility(medicamentRepository.findByCommonName("Prednisona").get(), medicamentRepository.findByCommonName("Insulina").get(), 1),
                new Incompatibility(medicamentRepository.findByCommonName("Atorvastatina").get(), medicamentRepository.findByCommonName("Metformina").get(), 1),
                new Incompatibility(medicamentRepository.findByCommonName("Claritromicina").get(), medicamentRepository.findByCommonName("Omeprazol").get(), 1)
            );

            incompatibilityRepository.saveAll(incompatibilities);



        }else {
            System.out.println("Ya existen incompatibilidades registradas en la base de datos. Seeder skiped");
        }
    }

}
