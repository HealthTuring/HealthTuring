package com.healthturing.healthturing_server.configs.seeders;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.repositories.IngredientRepository;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;

@Order(4)
@Component
public class MedicamentSeeder implements CommandLineRunner{

    private final MedicamentRepository medicamentRepository;
    private final IngredientRepository ingredientRepository;

    public MedicamentSeeder(MedicamentRepository medicamentRepository, IngredientRepository ingredientRepository){
        this.medicamentRepository=medicamentRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    @Override
    public void run(String... args){
        System.out.println("Creando lista de medicamentos:");


        if(medicamentRepository.count() == 0){
            Medicament med1 = new Medicament("Aspirina", "Acidum acetylsalicylicum");
            med1.setIngredients(List.of(ingredientRepository.findByName("Ácido acetilsalicílico").get()));

            Medicament med2 = new Medicament("Paracetamol", "Acetaminophenum");
            med2.setIngredients(List.of(ingredientRepository.findByName("Paracetamol").get()));

            Medicament med3 = new Medicament("Ibuprofeno", "Ibuprofenum");
            med3.setIngredients(List.of(ingredientRepository.findByName("Ibuprofeno").get()));

            Medicament med4 = new Medicament("Amoxicilina", "Amoxicillinum");
            med4.setIngredients(List.of(ingredientRepository.findByName("Amoxicilina").get()));

            Medicament med5 = new Medicament("Ciprofloxacino", "Ciprofloxacinum");
            med5.setIngredients(List.of(ingredientRepository.findByName("Ciprofloxacino").get()));

            Medicament med6 = new Medicament("Metformina", "Metforminum");
            med6.setIngredients(List.of(ingredientRepository.findByName("Metformina").get()));

            Medicament med7 = new Medicament("Atorvastatina", "Atorvastatinum");
            med7.setIngredients(List.of(ingredientRepository.findByName("Atorvastatina").get()));

            Medicament med8 = new Medicament("Omeprazol", "Omeprazolum");
            med8.setIngredients(List.of(ingredientRepository.findByName("Omeprazol").get()));

            Medicament med9 = new Medicament("Loratadina", "Loratadinum");
            med9.setIngredients(List.of(ingredientRepository.findByName("Loratadina").get()));

            Medicament med10 = new Medicament("Salbutamol", "Salbutamolum");
            med10.setIngredients(List.of(ingredientRepository.findByName("Salbutamol").get()));

            Medicament med11 = new Medicament("Levotiroxina", "Levothyroxinum");
            med11.setIngredients(List.of(ingredientRepository.findByName("Levotiroxina").get()));

            Medicament med12 = new Medicament("Enalapril", "Enalaprilum");
            med12.setIngredients(List.of(ingredientRepository.findByName("Enalapril").get()));

            Medicament med13 = new Medicament("Losartán", "Losartanum");
            med13.setIngredients(List.of(ingredientRepository.findByName("Losartán").get()));

            Medicament med14 = new Medicament("Metoprolol", "Metoprololum");
            med14.setIngredients(List.of(ingredientRepository.findByName("Metoprolol").get()));

            Medicament med15 = new Medicament("Diazepam", "Diazepamum");
            med15.setIngredients(List.of(ingredientRepository.findByName("Diazepam").get()));

            Medicament med16 = new Medicament("Clonazepam", "Clonazepamum");
            med16.setIngredients(List.of(
                ingredientRepository.findByName("Clonazepam").get(),
                ingredientRepository.findByName("Paracetamol").get()
            ));

            Medicament med17 = new Medicament("Sertralina", "Sertralinum");
            med17.setIngredients(List.of(ingredientRepository.findByName("Sertralina").get()));

            Medicament med18 = new Medicament("Fluoxetina", "Fluoxetinum");
            med18.setIngredients(List.of(ingredientRepository.findByName("Fluoxetina").get()));

            Medicament med19 = new Medicament("Insulina", "Insulinum");
            med19.setIngredients(List.of(ingredientRepository.findByName("Insulina").get()));

            Medicament med20 = new Medicament("Warfarina", "Warfarinum");
            med20.setIngredients(List.of(ingredientRepository.findByName("Warfarina").get()));

            Medicament med21 = new Medicament("Digoxina", "Digoxinum");
            med21.setIngredients(List.of(ingredientRepository.findByName("Digoxina").get()));

            Medicament med22 = new Medicament("Furosemida", "Furosemidum");
            med22.setIngredients(List.of(ingredientRepository.findByName("Furosemida").get()));

            Medicament med23 = new Medicament("Hidroclorotiazida", "Hydrochlorothiazidum");
            med23.setIngredients(List.of(ingredientRepository.findByName("Hidroclorotiazida").get()));

            Medicament med24 = new Medicament("Ranitidina", "Ranitidinum");
            med24.setIngredients(List.of(
                ingredientRepository.findByName("Ranitidina").get(),
                ingredientRepository.findByName("Ibuprofeno").get()
            )); 

            Medicament med25 = new Medicament("Esomeprazol", "Esomeprazolum");
            med25.setIngredients(List.of(ingredientRepository.findByName("Esomeprazol").get()));

            Medicament med26 = new Medicament("Cetirizina", "Cetirizinum");
            med26.setIngredients(List.of(ingredientRepository.findByName("Cetirizina").get()));

            Medicament med27 = new Medicament("Prednisona", "Prednisonum");
            med27.setIngredients(List.of(ingredientRepository.findByName("Prednisona").get()));

            Medicament med28 = new Medicament("Claritromicina", "Clarithromycinum");
            med28.setIngredients(List.of(
                ingredientRepository.findByName("Claritromicina").get(),
                ingredientRepository.findByName("Ácido acetilsalicílico").get()
            ));

            Medicament med29 = new Medicament("Tramadol", "Tramadolum");
            med29.setIngredients(List.of(ingredientRepository.findByName("Tramadol").get()));

            Medicament med30 = new Medicament("Codeína", "Codeinum");
            med30.setIngredients(List.of(ingredientRepository.findByName("Codeína").get()));

            Medicament med31 = new Medicament("AINEs", "AINEsum");
            med31.setIngredients(List.of(ingredientRepository.findByName("AINEs").get()));

            Medicament med32 = new Medicament("Alcohol", "Alcoholum");
            med32.setIngredients(List.of(ingredientRepository.findByName("Alcohol").get()));

            Medicament med33 = new Medicament("Calcio", "Calcioum");
            med33.setIngredients(List.of(ingredientRepository.findByName("Calcio").get()));

            Medicament med34 = new Medicament("Carbamazepina", "Carbamazepinaum");
            med34.setIngredients(List.of(ingredientRepository.findByName("Carbamazepina").get()));

            Medicament med35 = new Medicament("Clopidogrel", "Clopidogrelum");
            med35.setIngredients(List.of(ingredientRepository.findByName("Clopidogrel").get()));

            Medicament med36 = new Medicament("Espironolactona", "Espironolactonaum");
            med36.setIngredients(List.of(ingredientRepository.findByName("Espironolactona").get()));

            Medicament med37 = new Medicament("Ketoconazol", "Ketoconazolum");
            med37.setIngredients(List.of(ingredientRepository.findByName("Ketoconazol").get()));

            Medicament med38 = new Medicament("Metotrexato", "Metotrexatoum");
            med38.setIngredients(List.of(ingredientRepository.findByName("Metotrexato").get()));

            Medicament med39 = new Medicament("Verapamilo", "Verapamiloum");
            med39.setIngredients(List.of(ingredientRepository.findByName("Verapamilo").get()));




            medicamentRepository.saveAll(List.of(
                med1, med2, med3, med4, med5, med6, med7, med8, med9, med10,
                med11, med12, med13, med14, med15, med16, med17, med18, med19, med20,
                med21, med22, med23, med24, med25, med26, med27, med28, med29, med30,
                med31, med32, med33, med34, med35, med36, med37, med38, med39
            ));

            System.out.println("Se han creado los medicamentos adecuadamente");
        }else {
            System.out.println("Ya existen medicamentos registrados. Skipping seeder");
        }
    }
    
}
