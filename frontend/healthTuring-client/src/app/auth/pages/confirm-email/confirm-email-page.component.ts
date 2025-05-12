import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  imports: [],
  templateUrl: './confirm-email-page.component.html',
  styleUrl: './confirm-email-page.component.css'
})
export class ConfirmEmailPageComponent implements OnInit {

  private authService = inject(AuthService);
  private activatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    const token = this.activatedRoute.snapshot.paramMap.get('token') ?? '';
    this.authService.confirmEmail(token).subscribe();
  }

}
