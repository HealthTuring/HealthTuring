package com.healthturing.healthturing_server.dto;

import java.util.List;

public class MedicamentDTO {

    private String commonName;
    
    private String scientificName;

    private String dosageForm;

    private String drugRoute;

    private String strength;

    private String prospectUrl;

    private List<String> incompatibilities;

    public MedicamentDTO(){

    }

    public MedicamentDTO(String commonName, String scientificName, String dosageForm,
            String drugRoute, String strength, String prospectUrl, List<String> incompatibilities) {
        this.commonName=commonName;
        this.scientificName=scientificName;
        this.dosageForm=dosageForm;
        this.drugRoute=drugRoute;
        this.strength=strength;
        this.prospectUrl=prospectUrl;
        this.incompatibilities=incompatibilities;
    }
    
}
