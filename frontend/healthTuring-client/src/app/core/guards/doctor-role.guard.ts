import { inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtService } from '../services/jwt.service';

@Injectable({ providedIn: 'root' })
export class DoctorRoleGuard implements CanActivate {

    private jwtService = inject(JwtService);
    private router = inject(Router);

    canActivate(): boolean {
        if (this.jwtService.isAuthenticated() && this.jwtService.hasRole('ROLE_DOC')) {
            return true;
        }
        this.router.navigate(['/home']);
        return false;
    }
}