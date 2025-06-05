export interface AppointmentRequest {
  patientId: number;
  doctorId: number;
  date: string;     
  startTime: string; 
  reason: string;
}
