import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormUtils } from '../../../../utils/form-utils';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);
  formUtils = FormUtils;

  showPassword: boolean = false;

  loginForm = this.fb.group({
    email: ['user@mail.com', [Validators.required, Validators.pattern(FormUtils.emailPattern)]],
    password: ['$Userpass1', [Validators.required, Validators.pattern(FormUtils.passwordPattern)]]
  });

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
    console.log(this.showPassword);
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();
    console.log(this.loginForm.value);

    const { email = '', password = '' } = this.loginForm.value;

    this.authService.login(email!, password!).subscribe((isAuthenticated) => {
      console.log('Login response:', isAuthenticated);
      if (isAuthenticated) {
        console.log('Login successful');
        this.router.navigateByUrl('/home');
        return;
      }
    });

  }

}
