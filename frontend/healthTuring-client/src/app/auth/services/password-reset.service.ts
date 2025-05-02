import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, signal, computed, inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { catchError, map, Observable, of } from 'rxjs';
import { FORGET_PASSWORD_ENDPOINT, RESET_PASSWORD_ENDPOINT } from '../../config';

@Injectable({ providedIn: 'root' })
export class PasswordResetService {

  private http = inject(HttpClient);
  private toastr = inject(ToastrService);

  requestPasswordReset(email: string): Observable<boolean> {
    return this.http.post(FORGET_PASSWORD_ENDPOINT, email, { responseType: 'text' }).pipe(
      map(() => {
        this.toastr.success('Correo de restablecimiento enviado', 'Éxito', {
          timeOut: 3000,
          progressBar: true,
          closeButton: true,
        });
        return true;
      }),
      catchError((error) => this.handleError(error))
    );
  }

  resetPassword(token: string, newPassword: string): Observable<boolean> {
    return this.http.put(RESET_PASSWORD_ENDPOINT(token), newPassword, { responseType: 'text' }).pipe(
      map(() => {
        this.toastr.success('Contraseña restablecida correctamente', 'Éxito', {
          timeOut: 3000,
          progressBar: true,
          closeButton: true,
        });
        return true;
      }),
      catchError((error) => this.handleError(error))
    );
  }

  private handleError(error: HttpErrorResponse): Observable<boolean> {
    this.toastr.error(error.error, 'Error en el proceso de restablecimiento', {
      timeOut: 3000,
      progressBar: true,
      closeButton: true,
    });
    return of(false);
  }
}
