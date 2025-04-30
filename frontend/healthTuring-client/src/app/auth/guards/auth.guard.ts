import { inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    private authService = inject(AuthService);
    private router = inject(Router);

    canActivate(): boolean {
        const isAuthenticated = this.authService.authStatus() === 'authenticated';
        console.log('AuthsGuard: isAuthenticated =', isAuthenticated);

        if (!isAuthenticated) {
            this.router.navigate(['/auth/login']);
            return false;
        }

        return true;
    }
}