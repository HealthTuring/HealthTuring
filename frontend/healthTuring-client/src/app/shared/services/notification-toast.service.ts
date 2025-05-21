import { inject, Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({ providedIn: 'root' })
export class NotificationService {

  private toastr = inject(ToastrService);

  showSuccess(message: string, title: string): void {
    this.toastr.success(message, title, {
      timeOut: 5000,
      progressBar: true,
      closeButton: true,
    });
  }

  showError(message: string, title: string): void {
    this.toastr.error(message, title, {
      timeOut: 3000,
      progressBar: true,
      closeButton: true,
    });
  }
}
