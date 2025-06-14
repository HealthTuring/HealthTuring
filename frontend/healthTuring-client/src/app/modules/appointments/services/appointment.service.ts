import { inject, Injectable } from '@angular/core';
import { NotificationService } from '../../../shared/services/notification-toast.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AppointmentDto } from '../interfaces/appointment-dto.interface';
import { catchError, map, Observable, of } from 'rxjs';
import { APPOINTMENTS_BY_DOCTOR_ENDPOINT, APPOINTMENTS_BY_PATIENT_ENDPOINT, RESERVE_APPOINTMENT_ENDPOINT, SLOTS_ENDPOINT } from '../../../config';
import { AppointmentRequest } from '../interfaces/appointment-request.interface';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private http = inject(HttpClient);
  private toast = inject(NotificationService);

  getAppointmentsByPatient(patientId: number): Observable<AppointmentDto[]> {
    return this.http.get<AppointmentDto[]>(APPOINTMENTS_BY_PATIENT_ENDPOINT(patientId));
  }

  getAppointmentsByDoctor(doctorId: number): Observable<AppointmentDto[]> {
    return this.http.get<AppointmentDto[]>(APPOINTMENTS_BY_DOCTOR_ENDPOINT(doctorId));
  }

  getAvailableSlots(doctorId: number, date: string): Observable<string[]> {
        let params = new HttpParams().set('date', date);
        return this.http.get<string[]>(SLOTS_ENDPOINT(doctorId), { params });
    }

    reserveAppointment(request: AppointmentRequest): Observable<boolean> {
        return this.http.post(RESERVE_APPOINTMENT_ENDPOINT, request, { responseType: 'text' }).pipe(
            map((resp: string) => {
                this.toast.showSuccess(resp, 'Cita confirmada');
                return true;
            }),
            catchError((error) => {
                this.toast.showError(error.error, 'Reserva cancelada');
                return of(false);
            })
        );
    }

}
