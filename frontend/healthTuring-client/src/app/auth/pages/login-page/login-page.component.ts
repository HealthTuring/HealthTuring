import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormUtils } from '../../../../utils/form-utils';

@Component({
  imports: [ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  private fb = inject(FormBuilder);
  formUtils = FormUtils;

  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern(FormUtils.emailPattern)]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });


  onSubmit() {
    this.loginForm.markAllAsTouched();
    console.log(this.loginForm.value);
  }

}
