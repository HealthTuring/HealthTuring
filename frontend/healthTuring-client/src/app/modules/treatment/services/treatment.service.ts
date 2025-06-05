import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TREATMENTS_BY_PATIENT_ENDPOINT } from '../../../config';
import { TreatmentDto } from '../interfaces/treatment-dto.interface';
import { Page } from '../interfaces/page.interface';

@Injectable({ providedIn: 'root' })
export class TreatmentService {

    private http = inject(HttpClient);

    getTreatmentsByPatient(patientId: number): Observable<TreatmentDto[]> {
        return this.http.get<TreatmentDto[]>(TREATMENTS_BY_PATIENT_ENDPOINT(patientId));
    }

    getTreatmentsByPatientPaged(patientId: number, page = 0, size = 5) {
        return this.http.get<Page<TreatmentDto>>(
            `${TREATMENTS_BY_PATIENT_ENDPOINT(patientId)}/paged?page=${page}&size=${size}`
        );
    }

}