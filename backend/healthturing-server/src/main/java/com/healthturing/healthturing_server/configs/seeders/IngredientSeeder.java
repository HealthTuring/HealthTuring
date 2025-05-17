package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.healthturing.healthturing_server.models.Ingredient;
import com.healthturing.healthturing_server.repositories.IngredientRepository;

//@Order(1)
@Component
public class IngredientSeeder implements CommandLineRunner{

    private final IngredientRepository ingredientRepository;

    public IngredientSeeder(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args){
        System.out.println("Creando la lista de ingredientes");

        if(ingredientRepository.count() == 0){

            List<Ingredient> ingredients = List.of(
                new Ingredient("Ácido acetilsalicílico"),
                new Ingredient("Paracetamol"),
                new Ingredient("Ibuprofeno"),
                new Ingredient("Amoxicilina"),
                new Ingredient("Ciprofloxacino"),
                new Ingredient("Metformina"),
                new Ingredient("Atorvastatina"),
                new Ingredient("Omeprazol"),
                new Ingredient("Loratadina"),
                new Ingredient("Salbutamol"),
                new Ingredient("Levotiroxina"),
                new Ingredient("Enalapril"),
                new Ingredient("Losartán"),
                new Ingredient("Metoprolol"),
                new Ingredient("Diazepam"),
                new Ingredient("Clonazepam"),
                new Ingredient("Sertralina"),
                new Ingredient("Fluoxetina"),
                new Ingredient("Insulina"),
                new Ingredient("Warfarina"),
                new Ingredient("Digoxina"),
                new Ingredient("Furosemida"),
                new Ingredient("Hidroclorotiazida"),
                new Ingredient("Ranitidina"),
                new Ingredient("Esomeprazol"),
                new Ingredient("Cetirizina"),
                new Ingredient("Prednisona"),
                new Ingredient("Claritromicina"),
                new Ingredient("Tramadol"),
                new Ingredient("Codeína")
            );
            
            ingredientRepository.saveAll(ingredients);
            System.out.println("Ingredientes creados con éxito");

        }else{
            System.out.println("La base de datos ya tiene ingredientes registrados. Seeding skipped");
        }
    }
    
}



