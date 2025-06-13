export interface TreatmentDto {
    id:                number;
    nameMedication:    string;
    reason:            string;
    description:       string;
    strength:          string;
    dosageForm:        string;
    frequency:         string;
    drugRoute:         string;
    duration:          string;
    startDate:         Date;
    endDate:           Date;
    prospectUrl:       string;
    incompatibilities: Incompatibility[];
}

export interface Incompatibility {
    id:                     number;
    incompatibleMedication: null | string;
    incompatibleSubstance:  null | string;
}
