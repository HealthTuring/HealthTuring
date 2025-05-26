import { Component, effect, inject, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { TreatmentService } from '../../services/treatment.service';
import { TreatmentDto } from '../../interfaces/treatment-dto.interface';
import { PatientService } from '../../../../shared/services/patient.service';

@Component({
  imports: [CommonModule],
  templateUrl: './treatment-page.component.html',
  styleUrl: './treatment-page.component.css',
  providers: [DatePipe]
})
export class TreatmentPageComponent {

  private treatmentService = inject(TreatmentService);
  private datePipe = inject(DatePipe);
  private patientService = inject(PatientService);

  moreDetails: boolean[] = [];
  treatments: TreatmentDto[] = [];

  patientEffect = effect(() => {
    const patientId = this.patientService.getPatientId()();
    if (patientId != null) {
      this.getTreatmentByPatient(patientId);
    }
  });

  toggleDetails(id: number) {
    this.moreDetails[id] = !this.moreDetails[id];
  }

  getTreatmentByPatient(patientId: number) {
    this.treatmentService.getTreatmentsByPatient(patientId).subscribe(
      (treatments: TreatmentDto[]) => {
        this.treatments = treatments;
        this.moreDetails = this.treatments.map(() => false);
      },
    );
  }

  formatDate(dateStr: Date | null): string {
    if (!dateStr) return 'Indefinido';
    return this.datePipe.transform(dateStr, 'dd/MM/yyyy', 'UTC') || '';
  }

  formatIncompatibilities(incompatibilities: any[]): string {
    if (!Array.isArray(incompatibilities) || incompatibilities.length === 0) {
      return 'Ninguna';
    }
    return incompatibilities.map(i => i.incompatibleMedication || i.incompatibleSubstance).filter(Boolean).join(', ');
  }

}
