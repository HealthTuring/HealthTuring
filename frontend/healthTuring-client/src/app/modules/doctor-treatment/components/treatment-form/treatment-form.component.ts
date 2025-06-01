import { Component, EventEmitter, inject, OnInit, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MedicamentDto } from '../../interfaces/medicament-dto.interface';
import { MedicamentService } from '../../services/medicaments.service';
import { CommonModule } from '@angular/common';
import { PatientDto } from '../../../../shared/interfaces/patient-dto.interface';
import { PatientService } from '../../../../shared/services/patient.service';
import { JwtService } from '../../../../core/services/jwt.service';
import { DoctorService } from '../../services/doctor.service';
import { TreatmentCreateDto } from '../../interfaces/treatment-create-dto.interface';

@Component({
  selector: 'doctor-treatment-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './treatment-form.component.html',
  styleUrl: './treatment-form.component.css'
})
export class TreatmentFormComponent implements OnInit {

  private fb = inject(FormBuilder);
  private medicamentService = inject(MedicamentService);
  private patientService = inject(PatientService);
  private jwtService = inject(JwtService);
  private doctorService = inject(DoctorService);

  @Output() close = new EventEmitter<void>();

  medicaments: MedicamentDto[] = [];
  patients: PatientDto[] = [];

  treatmentForm = this.fb.group({
    reason: ['', [Validators.required]],
    startDate: ['', [Validators.required]],
    endDate: ['', [Validators.required]],
    dosesPerPeriod: ['', [Validators.required]],
    patientId:  [0, [Validators.required]],
    medicamentId: [0, [Validators.required]],
  })

  ngOnInit(): void {
    this.medicamentService.getAllMedicaments().subscribe({
      next: (medics) => this.medicaments = medics,
      error: (err) => console.error('Error al cargar medicamentos', err),
    });

    const doctorId = this.jwtService.getId();
    if (doctorId) {
      this.patientService.getPatientsByDoctor(doctorId).subscribe({
        next: (patients) => this.patients = patients,
        error: (err) => console.error('Error al cargar pacientes', err),
      });
    }
  }

  closeModal() {
    this.close.emit();
  }

  onSubmit(event: Event) {
    event.preventDefault();

    if (this.treatmentForm.invalid) {
      this.treatmentForm.markAllAsTouched();
      return;
    }

    const dto: TreatmentCreateDto = this.treatmentForm.value as TreatmentCreateDto;

    this.doctorService.createTreatment(dto).subscribe(success => {
      if (success) {
        this.close.emit();
      }
    });
  }
}
