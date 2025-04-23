import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./landing/landing.routes')
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.routes')
  },
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];
