import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';

import { catchError, map, Observable, of } from 'rxjs';
import { LOGIN_ENDPOINT } from '../../config';
import { Router } from '@angular/router';

type AuthStatus = 'checking' | 'authenticated' | 'not-authenticated';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private _authStatus = signal<AuthStatus>('checking');
    private _token = signal<string | null>(sessionStorage.getItem('token'));

    private http = inject(HttpClient);
    private router = inject(Router);

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
        return this.http.post<{token: string}>(LOGIN_ENDPOINT, { email, password }).pipe(
            map(({token}) => this.handleAuthSuccess(token)),
            catchError((error) => this.handleAuthError(error))
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
        this.logout();
        return of(false);
    }
}
