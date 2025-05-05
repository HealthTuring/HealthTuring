import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormUtils } from '../../../../utils/form-utils';
import { Router, RouterLink } from '@angular/router';
import { ResetPasswordService } from '../../services/reset-password.service';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './forget-password-page.component.html',
  styleUrl: './forget-password-page.component.css'
})
export class ForgetPasswordPageComponent {

  private fb = inject(FormBuilder);
  private router = inject(Router);
  private resetPasswordService = inject(ResetPasswordService);
  formUtils = FormUtils;

  forgetPasswordForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern(this.formUtils.emailPattern)]]
  });

  onSubmit() {
    this.forgetPasswordForm.markAllAsTouched();

    const email = this.forgetPasswordForm.controls['email'].value ?? '';

    this.resetPasswordService.requestPasswordReset(email).subscribe((success) => {
      if (success) {
        this.router.navigateByUrl('/auth/login')
      }
    });
  }

}
