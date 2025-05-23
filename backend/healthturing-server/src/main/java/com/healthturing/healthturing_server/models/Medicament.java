package com.healthturing.healthturing_server.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String dosageForm;

    private String drugRoute;

    private String strength;

    private String prospectUrl;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Incompatibility> incompatibilities = new HashSet<>();

    @OneToMany(mappedBy = "medicament")
    private List<Treatment> treatments;

    public Medicament() {

    }

    public Medicament(String commonName, String scientificName) {
        this.commonName = commonName;
        this.scientificName = scientificName;
    }

    public Medicament(String commonName, String scientificName, String dosageForm, String drugRoute,
            String strength, String prospectUrl) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.dosageForm = dosageForm;
        this.drugRoute = drugRoute;
        this.strength = strength;
        this.prospectUrl = prospectUrl;
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

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getDrugRoute() {
        return drugRoute;
    }

    public void setDrugRoute(String drugRoute) {
        this.drugRoute = drugRoute;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getProspectUrl() {
        return prospectUrl;
    }

    public void setProspectUrl(String prospectUrl) {
        this.prospectUrl = prospectUrl;
    }

    public Set<Incompatibility> getIncompatibilities() {
        return incompatibilities;
    }

    public void setIncompatibilities(Set<Incompatibility> incompatibilities) {
        this.incompatibilities = incompatibilities;
    }

    public void addIncompatibility(Incompatibility inc) {
        this.incompatibilities.add(inc);
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

}
