import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ResetPasswordService } from '../../services/reset-password.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormUtils } from '../../../../utils/form-utils';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './reset-password-page.component.html',
  styleUrl: './reset-password-page.component.css'
})
export class ResetPasswordPageComponent {

  private fb = inject(FormBuilder);
  private router: Router = inject(Router);
  private activatedRoute: ActivatedRoute = inject(ActivatedRoute);
  private resetPasswordService = inject(ResetPasswordService);
  formUtils = FormUtils;

  resetPasswordForm = this.fb.group({
    newPassword: ['', [Validators.required, Validators.pattern(this.formUtils.passwordPattern)]],
    confirmPassword: ['', [Validators.required, Validators.pattern(this.formUtils.passwordPattern)]],
  },
  {
    validators: [FormUtils.isFieldOneEqualFieldTwo('newPassword', 'confirmPassword')]
  });

  onSubmit() {

    const token = this.activatedRoute.snapshot.paramMap.get('token') ?? '';
    const newPassword = this.resetPasswordForm.controls['newPassword'].value ?? '';

    this.resetPasswordService.resetPassword(token, newPassword).subscribe((success) => {
      if (success) {
        this.router.navigateByUrl('/auth/login')
      } else {
        this.resetPasswordForm.reset();
      }
    })

  }

}
