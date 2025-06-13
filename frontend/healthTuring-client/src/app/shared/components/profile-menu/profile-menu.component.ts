import { Component, inject } from '@angular/core';
import { AuthService } from '../../../auth/services/auth.service';
import { JwtService } from '../../../core/services/jwt.service';

@Component({
  selector: 'shared-profile-menu',
  imports: [],
  templateUrl: './profile-menu.component.html',
  styleUrl: './profile-menu.component.css'
})
export class ProfileMenuComponent {

  private authService = inject(AuthService);
  jwtService = inject(JwtService);
  menuOpen = false;

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  logout() {
    this.authService.logout();
  }

}
