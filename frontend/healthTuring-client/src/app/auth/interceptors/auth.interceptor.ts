import { HttpHandlerFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export function authInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) {
  const authService = inject(AuthService);
  const token = authService.token();

  const publicEndpoints = [
    '/api/auth/login',
    '/api/auth/register',
  ];

  const isPublic = publicEndpoints.some(endpoint => req.url.includes(endpoint));

  if (isPublic) {
    return next(req); 
  }
  const newReq = req.clone({
    headers: req.headers.append('Authorization', `Bearer ${token}`),
  });
  return next(newReq);
}