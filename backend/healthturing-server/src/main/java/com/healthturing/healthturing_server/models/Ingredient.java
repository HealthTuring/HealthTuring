package com.healthturing.healthturing_server.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Medicament> medicaments = new ArrayList<>();

    @ManyToMany(mappedBy = "allergicIngredients")
    private List<Patient> allergicPatients = new ArrayList<>();


    public Ingredient() {}

    public Ingredient(String name) {
        this.name = name;
    }


}