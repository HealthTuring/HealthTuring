import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';

import { catchError, map, Observable, of } from 'rxjs';
import { CONFIRM_EMAIL_ENDPOINT, LOGIN_ENDPOINT, REGISTER_ENDPOINT } from '../../config';
import { NotificationService } from '../../shared/services/notification-toast.service';
import { JwtService } from '../../core/services/jwt.service';

type AuthStatus = 'checking' | 'authenticated' | 'not-authenticated';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private _authStatus = signal<AuthStatus>('checking');

  private http = inject(HttpClient);
  private router = inject(Router);
  private toast = inject(NotificationService);
  private jwt = inject(JwtService);

  authStatus = computed<AuthStatus>(() => this._authStatus());

  constructor() {
    this.jwt.init();
    this._authStatus.set(this.jwt.isAuthenticated() ? 'authenticated' : 'not-authenticated');
  }

  login(email: string, password: string): Observable<boolean> {
    return this.http.post(LOGIN_ENDPOINT, { email, password }, { responseType: 'text' }).pipe(
      map((token: string) => {
        this.jwt.setToken(token);
        this._authStatus.set('authenticated');
        return true;
      }),
      catchError((error) => {
        this.toast.showError(error.error, 'Error de Autenticación');
        this.logout();
        return of(false)
      })
    );
  }

  register(email: string, name: string, password: string): Observable<boolean> {
    return this.http.post(REGISTER_ENDPOINT, { email, name, password }, { responseType: 'text' }).pipe(
      map(() => {
        this.toast.showSuccess('Revisa tu correo para confirmar tu cuenta antes de iniciar sesión.', 'Registro exitoso');
        return true;
      }),
      catchError((error) => {
        this.toast.showError(error.error, 'Error de Autenticación');
        return of(false);
      })
    );
  }

  confirmEmail(token: string): Observable<boolean> {
    return this.http.put(CONFIRM_EMAIL_ENDPOINT, token, { responseType: 'text' }).pipe(
      map((resp) => {
        this.toast.showSuccess(resp, 'Validación exitosa');
        return true;
      }),
      catchError((error) => {
        this.toast.showError(error.error, 'Error de Verificación');
        return of(false);
      })
    )
  }

  logout() {
    this.jwt.clear();
    this._authStatus.set('not-authenticated');
    this.router.navigateByUrl('/auth/login', { replaceUrl: true });
  }

}
