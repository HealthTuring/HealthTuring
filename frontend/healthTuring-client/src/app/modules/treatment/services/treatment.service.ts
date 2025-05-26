import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { TREATMENTS_BY_PATIENT_ENDPOINT } from '../../../config';
import { TreatmentDto } from '../interfaces/treatment-dto.interface';
import { NotificationService } from '../../../shared/services/notification-toast.service';

@Injectable({providedIn: 'root'})
export class TreatmentService {

    private http = inject(HttpClient);
    private toast = inject(NotificationService);

    getTreatmentsByPatient(patientId: number): Observable<TreatmentDto[]> {
        return this.http.get<TreatmentDto[]>(TREATMENTS_BY_PATIENT_ENDPOINT(patientId)).pipe(
            catchError((error) => {
                this.toast.showError(error.error, 'Error');
                return of([]);
            }),   
        );
    }

    
}