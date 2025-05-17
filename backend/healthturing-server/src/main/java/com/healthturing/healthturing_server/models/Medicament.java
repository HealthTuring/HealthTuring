package com.healthturing.healthturing_server.models;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String commonName;

    @Column(unique = true, nullable = false)
    private String scientificName;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incompatibility> incompatibilities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "medicament_ingredients",
        joinColumns = @JoinColumn(name = "medicament_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>();



    public Medicament(){

    }

    public Medicament(String commonName, String scientificName){
        this.commonName=commonName;
        this.scientificName=scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public List<Incompatibility> getIncompatibilities() {
        return incompatibilities;
    }

    public void setIncompatibilities(List<Incompatibility> incompatibilities) {
        this.incompatibilities = incompatibilities;
    }

    public void addIncompatibility(Incompatibility inc){
        this.incompatibilities.add(inc);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.addMedicament(this);
    }


    


    
    

  

    
    
}
