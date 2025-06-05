package com.healthturing.healthturing_server.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    private String duration;

    private String dosesPerPeriod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicament_id")
    private Medicament medicament;

    public Treatment() {}

    public Treatment(String reason, LocalDate startDate, LocalDate endDate, String duration, String dosesPerPeriod, Patient patient, Medicament medicament) {
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.dosesPerPeriod = dosesPerPeriod;
        this.patient = patient;
        this.medicament = medicament;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDosesPerPeriod() {
        return dosesPerPeriod;
    }

    public void setDosesPerPeriod(String dosesPerPeriod) {
        this.dosesPerPeriod = dosesPerPeriod;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}