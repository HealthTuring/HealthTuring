export interface TreatmentCreateDto {
  reason: string;
  startDate: string;
  endDate: string;
  dosesPerPeriod: string;
  patientId: number;
  medicamentId: number;
}
