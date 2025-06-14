import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MedicamentDto } from '../../interfaces/medicament-dto.interface';
import { MedicamentService } from '../../services/medicaments.service';
import { CommonModule } from '@angular/common';
import { PatientDto } from '../../../../shared/interfaces/patient-dto.interface';
import { PatientService } from '../../../../shared/services/patient.service';
import { JwtService } from '../../../../core/services/jwt.service';
import { DoctorService } from '../../services/doctor.service';
import { TreatmentCreateDto } from '../../interfaces/treatment-create-dto.interface';
import { TreatmentDto } from '../../../treatment/interfaces/treatment-dto.interface';
import { TreatmentUpdateDto } from '../../interfaces/treatment-update-dto.interface';
import { FormUtils } from '../../../../../utils/form-utils';

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
  formUtils = FormUtils;

  @Output() close = new EventEmitter<void>();
  @Output() treatmentCreated = new EventEmitter<void>();
  @Input() treatmentToEdit: TreatmentDto | null = null;
  @Output() treatmentUpdated = new EventEmitter<void>();


  medicaments: MedicamentDto[] = [];
  patients: PatientDto[] = [];

  treatmentForm!: FormGroup;

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

if (this.treatmentToEdit) {

    this.treatmentForm = this.fb.group({
      reason: [this.treatmentToEdit.reason, [Validators.required]],
      startDate: [this.dateToInputString(this.treatmentToEdit.startDate), [Validators.required]],
      endDate: [this.dateToInputString(this.treatmentToEdit.endDate)],
      dosesPerPeriod: [this.treatmentToEdit.frequency, [Validators.required]],

    });
  } else {

    this.treatmentForm = this.fb.group({
      reason: ['', [Validators.required]],
      startDate: ['', [Validators.required]],
      endDate: [''],
      dosesPerPeriod: ['', [Validators.required]],
      patientId: ['', [Validators.required]],
      medicamentId: ['', [Validators.required]],
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

    if (this.treatmentToEdit) {
      const dto: TreatmentUpdateDto = this.treatmentForm.value as TreatmentUpdateDto;
      this.doctorService.editTreatment(this.treatmentToEdit.id, dto).subscribe(success => {
        if (success) {
          this.treatmentUpdated.emit();
          this.close.emit();
        }
      });
    } else {
      const formValue = this.treatmentForm.value;
      const dto: TreatmentCreateDto = {
        reason: formValue.reason ?? '',
        startDate: formValue.startDate ?? '',
        endDate: formValue.endDate ?? '',
        dosesPerPeriod: formValue.dosesPerPeriod ?? '',
        patientId: Number(formValue.patientId),
        medicamentId: Number(formValue.medicamentId),
      };
      this.doctorService.createTreatment(dto).subscribe(success => {
        if (success) {
          this.treatmentCreated.emit();
          this.close.emit();
        }
      });
    }
  }

  dateToInputString(date?: Date | null): string | null {
    if (!date) return null;
    if (typeof date === 'string') return date;
    return date.toISOString().split('T')[0];
  }
}
