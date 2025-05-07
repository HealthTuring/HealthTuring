import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';

import { catchError, map, Observable, of } from 'rxjs';
import { LOGIN_ENDPOINT, REGISTER_ENDPOINT } from '../../config';
import { ToastrService } from 'ngx-toastr';

type AuthStatus = 'checking' | 'authenticated' | 'not-authenticated';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private _authStatus = signal<AuthStatus>('checking');
    private _token = signal<string | null>(sessionStorage.getItem('token'));

    private http = inject(HttpClient);
    private router = inject(Router);
    private toastr = inject(ToastrService);

    authStatus = computed<AuthStatus>(() => this._authStatus());
    token = computed(this._token);

    constructor() {
        const token = sessionStorage.getItem('token');
        if (token) {
            this._authStatus.set('authenticated');
        } else {
            this._authStatus.set('not-authenticated');
        }
    }

    login(email: string, password: string): Observable<boolean> {
        return this.http.post(LOGIN_ENDPOINT, { email, password }, { responseType: 'text' }).pipe(
            map((token: string) => {
                return this.handleAuthSuccess(token);
            }),
            catchError((error) => this.handleAuthError(error))
        );
    }

    register(email: string, name: string, password: string): Observable<boolean> {
        return this.http.post(REGISTER_ENDPOINT, { email, name, password }, { responseType: 'text' }).pipe(
            map(() => {
            this.toastr.success('Revisa tu correo para confirmar tu cuenta antes de iniciar sesión.', 'Registro exitoso', {
                timeOut: 5000,
                progressBar: true,
                closeButton: true,
            });
            return true;
            }),
            catchError((error) => {
                this.toastr.error(error.error, 'Error de Autenticación', {
                    timeOut: 3000,
                    progressBar: true,
                    closeButton: true,
                });
                return of(false);
            })
        );
    }

    logout() {
        this._token.set(null);
        this._authStatus.set('not-authenticated');
        sessionStorage.removeItem('token');
        this.router.navigateByUrl('/auth/login', { replaceUrl: true });
    }

    private handleAuthSuccess(token: string) {
        this._authStatus.set('authenticated');
        this._token.set(token);
        sessionStorage.setItem('token', token);
        return true;
    }

    private handleAuthError(error: HttpErrorResponse) {
        this.toastr.error(error.error, 'Error de Autenticación', {
            timeOut: 3000,
            progressBar: true,
            closeButton: true,
        });
        this.logout();
        return of(false);
    }
}
