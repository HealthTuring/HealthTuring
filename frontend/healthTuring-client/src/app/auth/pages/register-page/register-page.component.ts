import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { FormUtils } from '../../../../utils/form-utils';
import { AuthService } from '../../services/auth.service';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {

  private fb = inject(FormBuilder);
  private router = inject(Router);
  private authService = inject(AuthService);
  formUtils = FormUtils;

  showPassword: boolean = false;

  registerForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.pattern(FormUtils.emailPattern)]],
    password: ['', [Validators.required, Validators.pattern(FormUtils.passwordPattern)]],
    isDoctor: [false],
  });

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    this.registerForm.markAllAsTouched();

    if (this.registerForm.valid) {
      const { name, email, password } = this.registerForm.value ?? {};

      this.authService.register(email!, name!, password!).subscribe((isAuthenticated) => {
        if (isAuthenticated) {
          this.router.navigateByUrl('/auth/login');
          return;
        }
      });
    }
  }

}
