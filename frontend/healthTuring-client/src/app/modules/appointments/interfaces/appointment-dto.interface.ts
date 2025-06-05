export interface AppointmentDto {
    id:          number;
    date:        Date;
    startTime:   string;
    endTime:     string;
    reason:      string;
    patientId:   number;
    patientName: string;
}
