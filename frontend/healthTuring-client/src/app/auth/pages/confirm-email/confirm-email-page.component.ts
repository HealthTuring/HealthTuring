import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  imports: [],
  templateUrl: './confirm-email-page.component.html',
  styleUrl: './confirm-email-page.component.css'
})
export class ConfirmEmailPageComponent implements OnInit, OnDestroy {

  private authService = inject(AuthService);
  private activatedRoute = inject(ActivatedRoute);
  private timeoutId: any;

  ngOnInit(): void {
    const token = this.activatedRoute.snapshot.paramMap.get('token') ?? '';
    this.authService.confirmEmail(token).subscribe(() => {
      this.timeoutId = setTimeout(() => {
        window.close();
      }, 10000);
    });
  }

  closeWindow(): void {
    window.close();
  }

  ngOnDestroy(): void {
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }
  }

}
