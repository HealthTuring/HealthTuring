import { inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtService } from '../services/jwt.service';

@Injectable({ providedIn: 'root' })
export class UserRoleGuard implements CanActivate {

    private jwtService = inject(JwtService);
    private router = inject(Router);

    canActivate(): boolean {
        if (this.jwtService.isAuthenticated() && this.jwtService.hasRole('ROLE_USER')) {
            return true;
        }
        // Opcional: redirigir al login o a alguna página de error
        this.router.navigate(['/home']);
        return false;
    }
}