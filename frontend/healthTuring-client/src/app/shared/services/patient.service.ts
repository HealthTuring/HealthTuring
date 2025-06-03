import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { PATIENT_DATA_ENDPOINT, PATIENTS_NAMES_BYDOCTOR_ENDPOINT, PATIENTS_NAMES_BYUSER_ENDPOINT } from '../../config';
import { PatientDto } from '../interfaces/patient-dto.interface';
import { PatientDataDto } from '../../modules/patient-profile/interfaces/patient-data-dto.interface';

@Injectable({ providedIn: 'root' })
export class PatientService {

    private http = inject(HttpClient);
    private selectedPatientId = signal<number | null>(null);
    private selectedPatient = signal<PatientDto | null>(null);

    getPatientsByUser(userId: number): Observable<PatientDto[]> {
        return this.http.get<PatientDto[]>(PATIENTS_NAMES_BYUSER_ENDPOINT(userId));
    }

    getPatientsByDoctor(doctorId: number): Observable<PatientDto[]> {
        return this.http.get<PatientDto[]>(PATIENTS_NAMES_BYDOCTOR_ENDPOINT(doctorId));
    }

    getAllPatientdata(patiendId: number): Observable<PatientDataDto> {
        return this.http.get<PatientDataDto>(PATIENT_DATA_ENDPOINT(patiendId));
    }

    setPatientId(id: number) {
        this.selectedPatientId.set(id);
    }

    getPatientId() {
        return this.selectedPatientId.asReadonly();
    }

    setPatient(patientDto: PatientDto) {
        this.selectedPatient.set(patientDto);
    }

    getPatient() {
        return this.selectedPatient.asReadonly();
    }

}
