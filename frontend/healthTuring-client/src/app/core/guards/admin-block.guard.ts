import { inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtService } from '../services/jwt.service';

@Injectable({ providedIn: 'root' })
export class AdminBlockGuard implements CanActivate {

  private jwtService = inject(JwtService);
  private router = inject(Router);

  canActivate(): boolean {
    const isAuthenticated = this.jwtService.isAuthenticated();
    const isAdmin = this.jwtService.hasRole('ROLE_ADMIN');

    if (isAuthenticated && isAdmin) {
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }
}
