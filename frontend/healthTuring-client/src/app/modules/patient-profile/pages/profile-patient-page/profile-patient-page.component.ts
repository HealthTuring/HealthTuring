import { CommonModule } from '@angular/common';
import { Component, effect, inject } from '@angular/core';
import { PatientService } from '../../../../shared/services/patient.service';
import { PatientDataDto } from '../../interfaces/patient-data-dto.interface';

@Component({
  imports: [CommonModule],
  templateUrl: './profile-patient-page.component.html',
  styleUrl: './profile-patient-page.component.css'
})
export class ProfilePatientPageComponent {

  private patientService = inject(PatientService);
  patient?: PatientDataDto;
  loading = true;

  patientEffect = effect(() => {
    const patientId = this.patientService.getPatientId()();
    if (patientId != null) {
      this.getPatientData(patientId);
    }
  });

  getPatientData(patiendId: number) {
    this.loading = true;
    this.patientService.getAllPatientdata(patiendId).subscribe({
      next: (data) => {
        this.patient = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
      }
    });
  }

  getGenderLabel(gender: string) {
    return gender === 'M' ? 'Masculino' : gender === 'F' ? 'Femenino' : 'Otro';
  }

  getRhLabel(rh: string) {
    switch (rh) {
      case 'POSITIVE': return '+';
      case 'NEGATIVE': return '-';
      default: return '';
    }
  }
}

