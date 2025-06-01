import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { TreatmentCreateDto } from '../interfaces/treatment-create-dto.interface';
import { catchError, map, Observable, of } from 'rxjs';
import { CREATE_TREATMENTS_ENDPOINT } from '../../../config';
import { NotificationService } from '../../../shared/services/notification-toast.service';
import { ApiResponseDto } from '../../../shared/interfaces/response-dto.interface';

@Injectable({ providedIn: 'root' })
export class DoctorService {

  private http = inject(HttpClient);
  private toast = inject(NotificationService);

  createTreatment(dto: TreatmentCreateDto): Observable<boolean> {
    return this.http.post<ApiResponseDto>(CREATE_TREATMENTS_ENDPOINT, dto).pipe(
      map((resp) => {
        this.toast.showSuccess(resp.message, "Operación exitosa");
        return true;
      }),
      catchError((error) => {
        this.toast.showSuccess(error.message, "Error al crear tratamiento");
        return of(false)
      })
    )
  }


}
