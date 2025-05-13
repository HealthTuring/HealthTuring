import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  imports: [RouterOutlet],
  templateUrl: './auth-layout.component.html',
  styleUrl: './auth-layout.component.css'
})
export class AuthLayoutComponent {
  imageSrc = 'assets/img/default.webp';

  private imageMap: { [key: string]: string } = {
    '/auth/login': 'assets/img/login.webp',
    '/auth/register': 'assets/img/register.webp',
    '/auth/forget-password': 'assets/img/forget-password.webp',
    '/auth/reset-password': 'assets/img/reset-password.webp',
    '/auth/confirm-email': 'assets/img/confirm-email.webp',
  };

  constructor(private router: Router) {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        const path = event.urlAfterRedirects;

        // Buscar coincidencia que comience con la clave (rutas base)
        const matchedKey = Object.keys(this.imageMap).find(key => path.startsWith(key));

        this.imageSrc = matchedKey ? this.imageMap[matchedKey] : 'assets/img/default.webp';
      });

  }
}