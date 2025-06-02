import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'confirm-modal',
  imports: [],
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.css'
})
export class ConfirmModalComponent {

  @Input() show = false;
  @Input() title = '';
  @Input() message = '';
  @Output() onConfirm = new EventEmitter<void>();
  @Output() onCancel = new EventEmitter<void>();

  confirm() { this.onConfirm.emit(); }
  cancel() { this.onCancel.emit(); }

}
