import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { TreatmentCreateDto } from '../interfaces/treatment-create-dto.interface';
import { catchError, map, Observable, of } from 'rxjs';
import { CREATE_TREATMENTS_ENDPOINT, DELETE_TREATMENTS_ENDPOINT, EDIT_TREATMENTS_ENDPOINT } from '../../../config';
import { NotificationService } from '../../../shared/services/notification-toast.service';
import { ApiResponseDto } from '../../../shared/interfaces/response-dto.interface';
import { TreatmentUpdateDto } from '../interfaces/treatment-update-dto.interface';

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
        const backendMsg = error?.error?.message || 'Ha ocurrido un error inesperado';
        this.toast.showError(backendMsg, "Error al crear tratamiento");
        return of(false);
      })
    )
  }

  deleteTreatment(id: number): Observable<boolean> {
    return this.http.delete<ApiResponseDto>(DELETE_TREATMENTS_ENDPOINT(id)).pipe(
      map((resp) => {
        this.toast.showSuccess(resp.message, "Tratamiento eliminado");
        return true;
      }),
      catchError((error) => {
        const backendMsg = error?.error?.message || 'Ha ocurrido un error inesperado';
        this.toast.showError(backendMsg, "Error al eliminar tratamiento");
        return of(false);
      })
    );
  }

  editTreatment(id: number, dto: TreatmentUpdateDto): Observable<boolean> {
    return this.http.put<ApiResponseDto>(EDIT_TREATMENTS_ENDPOINT(id), dto).pipe(
      map((resp) => {
        this.toast.showSuccess(resp.message, "Tratamiento actualizado");
        return true;
      }),
      catchError((error) => {
        const backendMsg = error?.error?.message || 'Ha ocurrido un error inesperado';
        this.toast.showError(backendMsg, "Error al editar tratamiento");
        return of(false);
      })
    );
  }


}
