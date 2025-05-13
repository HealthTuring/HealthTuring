import { Component, inject } from '@angular/core';
import { AuthService } from '../../../auth/services/auth.service';
import { HeaderComponent } from '../../../shared/components/header/header.component';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  imports: [HeaderComponent, NavbarComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  authService = inject(AuthService);

}
