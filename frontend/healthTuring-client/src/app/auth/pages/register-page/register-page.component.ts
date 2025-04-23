import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { FormUtils } from '../../../../utils/form-utils';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {

  private fb = inject(FormBuilder);
  formUtils = FormUtils;
  showPassword: boolean = false;

  loginForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.pattern(FormUtils.emailPattern)]],
    password: ['', [Validators.required, Validators.pattern(FormUtils.passwordPattern)]],
    isDoctor: [false],
  });

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
    console.log('hola')
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();
  }

}
