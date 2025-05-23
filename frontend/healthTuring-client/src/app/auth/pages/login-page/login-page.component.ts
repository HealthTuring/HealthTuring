import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormUtils } from '../../../../utils/form-utils';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { JwtService } from '../../../core/services/jwt.service';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  private fb = inject(FormBuilder);
  private router = inject(Router);
  private authService = inject(AuthService);
  private jwtService = inject(JwtService);
  formUtils = FormUtils;

  showPassword: boolean = false;

  loginForm = this.fb.group({
    email: ['user@mail.com', [Validators.required, Validators.pattern(FormUtils.emailPattern)]],
    password: ['$Userpass1', [Validators.required, Validators.pattern(FormUtils.passwordPattern)]]
  });

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();

    if (this.loginForm.valid) {
      const { email = '', password = '' } = this.loginForm.value;

      this.authService.login(email!, password!).subscribe((isAuthenticated) => {
        if (isAuthenticated) {
          const role = this.jwtService.getRole();
          if (role === 'ROLE_ADMIN') {
            window.location.href = 'http://localhost:8080/vista';
          } else {
            this.router.navigateByUrl('/home');
          }
        }
      });
    }

  }

}
