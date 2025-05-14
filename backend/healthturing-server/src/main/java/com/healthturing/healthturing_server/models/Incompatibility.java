package com.healthturing.healthturing_server.models;

import jakarta.persistence.Column;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicament_id")
    private Medicament medicament;

    @ManyToOne(optional = false)
    @JoinColumn(name = "incompatible_medicament_id")
    private Medicament incompatibleWith;

    @Column(nullable = false)
    private int level;

    public Incompatibility() {}

    public Incompatibility(Medicament medicament, Medicament incompatibleWith, int level) {
        this.medicament = medicament;
        this.incompatibleWith = incompatibleWith;
        this.level = level;
    }


}
