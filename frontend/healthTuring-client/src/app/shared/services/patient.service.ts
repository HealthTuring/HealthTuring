import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { PATIENTS_NAMES_BYUSER_ENDPOINT } from '../../config';
import { Observable } from 'rxjs';
import { PatientDto } from '../interfaces/patient-dto.interface';

@Injectable({ providedIn: 'root' })
export class PatientService {

    private http = inject(HttpClient);

    getPatientsByUser(userId: number): Observable<PatientDto[]> {
        return this.http.get<PatientDto[]>(PATIENTS_NAMES_BYUSER_ENDPOINT(userId));
    }

}