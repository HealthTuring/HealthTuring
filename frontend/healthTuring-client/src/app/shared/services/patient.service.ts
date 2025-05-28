import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { DOCTOR_BY_PATIENT, PATIENTS_NAMES_BYDOCTOR_ENDPOINT, PATIENTS_NAMES_BYUSER_ENDPOINT } from '../../config';
import { Observable } from 'rxjs';
import { PatientDto } from '../interfaces/patient-dto.interface';
import { DoctorDto } from '../interfaces/doctor-dto.interface';

@Injectable({ providedIn: 'root' })
export class PatientService {

    private http = inject(HttpClient);
    private selectedPatientId = signal<number | null>(null);

    getPatientsByUser(userId: number): Observable<PatientDto[]> {
        return this.http.get<PatientDto[]>(PATIENTS_NAMES_BYUSER_ENDPOINT(userId));
    }

    getPatientsByDoctor(doctorId: number): Observable<PatientDto[]> {
        return this.http.get<PatientDto[]>(PATIENTS_NAMES_BYDOCTOR_ENDPOINT(doctorId));
    }

    setPatientId(id: number) {
        this.selectedPatientId.set(id);
    }

    getPatientId() {
        return this.selectedPatientId.asReadonly();
    }

    getDoctorByPatientId(patientId: number): Observable<DoctorDto> {
      return this.http.get<DoctorDto>(DOCTOR_BY_PATIENT(patientId));
    }

}
