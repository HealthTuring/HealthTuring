import { Routes } from '@angular/router';
import { AuthGuard } from './auth/guards/auth.guard';
import { AdminBlockGuard } from './core/guards/admin-block.guard';

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
    path: 'home',
    loadChildren: () => import('./modules/modules.routes'),
    canActivate: [AuthGuard, AdminBlockGuard]
  },
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];
