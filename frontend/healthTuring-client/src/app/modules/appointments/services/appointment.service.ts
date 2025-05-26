import { inject, Injectable } from '@angular/core';
import { NotificationService } from '../../../shared/services/notification-toast.service';
import { HttpClient } from '@angular/common/http';
import { AppointmentDto } from '../interfaces/appointment-dto.interface';
import { catchError, Observable, of } from 'rxjs';
import { APPOINTMENTS_BY_PATIENT_ENDPOINT } from '../../../config';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private http = inject(HttpClient);
  private toast = inject(NotificationService);

  getAppointmentsByPatient(patientId: number): Observable<AppointmentDto[]> {
    return this.http.get<AppointmentDto[]>(APPOINTMENTS_BY_PATIENT_ENDPOINT(patientId)).pipe(
      catchError((error) => {
        this.toast.showError(error.error, 'Error');
        return of([]);
      })
    );
  }

}
