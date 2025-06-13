import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { CREATE_PATIENT_ENDPOINT, PATIENT_DATA_ENDPOINT, PATIENTS_NAMES_BYDOCTOR_ENDPOINT, PATIENTS_NAMES_BYUSER_ENDPOINT } from '../../config';
import { PatientDto } from '../interfaces/patient-dto.interface';
import { PatientDataDto } from '../../modules/patient-profile/interfaces/patient-data-dto.interface';
import { PatientCreateDTO } from '../interfaces/patient-create-dto.interface';
import { ApiResponseDto } from '../interfaces/response-dto.interface';
import { NotificationService } from './notification-toast.service';

@Injectable({ providedIn: 'root' })
export class PatientService {

    private http = inject(HttpClient);
    private toast = inject(NotificationService);
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

    createPatientForUser(userId: number, dto: PatientCreateDTO): Observable<boolean> {
        return this.http.post<ApiResponseDto>(CREATE_PATIENT_ENDPOINT(userId), dto).pipe(
            map((resp) => {
                this.toast.showSuccess(resp.message, "Proceso completado con éxito");
                return true;
            }),
            catchError((error) => {
                const backendMsg = error?.error?.message || 'Ha ocurrido un error inesperado';
                this.toast.showError(backendMsg, "Error al crear el paciente");
                return of(false);
            })
        )
    }
}


