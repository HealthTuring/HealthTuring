import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'doctor-treatment-form',
  imports: [],
  templateUrl: './treatment-form.component.html',
  styleUrl: './treatment-form.component.css'
})
export class TreatmentFormComponent {

    @Output() close = new EventEmitter<void>();

  closeModal() {
    this.close.emit();
  }

  onSubmit(event: Event) {
    event.preventDefault();
    // Lógica de guardado
    this.close.emit();
  }

}
