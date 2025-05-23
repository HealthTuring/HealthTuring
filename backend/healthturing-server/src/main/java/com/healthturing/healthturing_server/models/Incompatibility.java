package com.healthturing.healthturing_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incompatibilities")
public class Incompatibility {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medicament medicament;

    @ManyToOne
    @JoinColumn(name = "incompatible_medicament_id")
    private Medicament incompatibleMedicament;

    @ManyToOne
    @JoinColumn(name = "incompatible_substance_id")
    private Substance incompatibleSubstance;

    public Incompatibility() {}

    public Incompatibility(Medicament medicament, Medicament incompatibleMedicament, Substance incompatibleSubstance) {
        this.medicament = medicament;
        this.incompatibleMedicament = incompatibleMedicament;
        this.incompatibleSubstance = incompatibleSubstance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Medicament getIncompatibleMedicament() {
        return incompatibleMedicament;
    }

    public void setIncompatibleMedicament(Medicament incompatibleMedicament) {
        this.incompatibleMedicament = incompatibleMedicament;
    }

    public Substance getIncompatibleSubstance() {
        return incompatibleSubstance;
    }

    public void setIncompatibleSubstance(Substance incompatibleSubstance) {
        this.incompatibleSubstance = incompatibleSubstance;
    }

}
