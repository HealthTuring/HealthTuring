import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { JwtService } from '../../../core/services/jwt.service';
import { PatientCreateDTO } from '../../interfaces/patient-create-dto.interface';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'create-patient-modal',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './create-patient-modal.component.html',
  styleUrl: './create-patient-modal.component.css'
})
export class CreatePatientModalComponent {

  private fb = inject(FormBuilder);
  private patientService = inject(PatientService);
  private jwtService = inject(JwtService);

  @Output() patientCreated = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  loading = false;
  error: string | null = null;
  today: string = new Date().toISOString().split('T')[0];

  bloodGroups = ['A', 'B', 'AB', 'O'];
  rhFactors = ['POSITIVE', 'NEGATIVE'];

  patientForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(50)]],
      dni: ['', [Validators.required, Validators.maxLength(20)]],
      dateOfBirth: ['', Validators.required],
      gender: ['M', Validators.required],
      bloodGroup: ['A', Validators.required],
      rhFactor: ['POSITIVE', Validators.required],
      emergencyContact: ['', [Validators.required, Validators.maxLength(30)]]
    });;

  submitForm() {
    if (this.patientForm.invalid) return;
    this.loading = true;
    this.error = null;

    const userId = this.jwtService.getId() ?? 0;
    const dto: PatientCreateDTO = this.patientForm.value as PatientCreateDTO;

    this.patientService.createPatientForUser(userId, dto).subscribe((isCreated) => {
        this.loading = false;
        this.patientCreated.emit();
        this.close.emit();
    });
  }

  closeModal() {
    this.close.emit();
  }
}
