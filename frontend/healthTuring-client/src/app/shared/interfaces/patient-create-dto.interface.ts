export interface PatientCreateDTO {
  name: string;
  dni: string;
  dateOfBirth: string;
  gender: 'M' | 'F';
  bloodGroup: 'A' | 'B' | 'AB' | 'O';
  rhFactor: 'POSITIVE' | 'NEGATIVE';
  emergencyContact: string;
}