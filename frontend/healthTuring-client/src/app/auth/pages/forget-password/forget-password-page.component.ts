import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormUtils } from '../../../../utils/form-utils';
import { Router, RouterLink } from '@angular/router';
import { PasswordResetService } from '../../services/password-reset.service';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './forget-password-page.component.html',
  styleUrl: './forget-password-page.component.css'
})
export class ForgetPasswordPageComponent {

  private fb = inject(FormBuilder);
  private router = inject(Router);
  private passwordResetService = inject(PasswordResetService);
  formUtils = FormUtils;

  forgetPasswordForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern(this.formUtils.emailPattern)]]
  });

  onSubmit() {
    this.forgetPasswordForm.markAllAsTouched();

    const email = this.forgetPasswordForm.controls['email'].value ?? '';

    this.passwordResetService.requestPasswordReset(email).subscribe((success) => {
      if (success) {
        this.router.navigateByUrl('/auth/login')
      }
    })
  }



}
