package com.healthturing.healthturing_server.dto;
 import java.util.List;

public class AdminMedicamentDTO {
    
   


    private String commonName;
    
    private String scientificName;

    private String dosageForm;

    private String drugRoute;

    private String strength;

    private String prospectUrl;

    private List<String> incompatibilities;

    public AdminMedicamentDTO(){

    }

    public AdminMedicamentDTO(String commonName, String scientificName, String dosageForm,
            String drugRoute, String strength, String prospectUrl, List<String> incompatibilities) {
        this.commonName=commonName;
        this.scientificName=scientificName;
        this.dosageForm=dosageForm;
        this.drugRoute=drugRoute;
        this.strength=strength;
        this.prospectUrl=prospectUrl;
        this.incompatibilities=incompatibilities;
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

    public List<String> getIncompatibilities() {
        return incompatibilities;
    }

    public void setIncompatibilities(List<String> incompatibilities) {
        this.incompatibilities = incompatibilities;
    }

}
