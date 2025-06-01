import { CommonModule, DatePipe } from '@angular/common';
import { Component, effect, inject, Input } from '@angular/core';
import { TreatmentDto } from '../../../treatment/interfaces/treatment-dto.interface';
import { TreatmentService } from '../../../treatment/services/treatment.service';
import { PatientService } from '../../../../shared/services/patient.service';

@Component({
  selector: 'treatment-table',
  imports: [CommonModule],
  templateUrl: './treatment-table.component.html',
  styleUrl: './treatment-table.component.css',
  providers: [DatePipe]
})
export class TreatmentTableComponent {

    private datePipe = inject(DatePipe);
  private treatmentService = inject(TreatmentService);
  private patientService = inject(PatientService);

  treatments: TreatmentDto[] = [];

  moreDetails: boolean = false;

  patientEffect = effect(() => {
    const patientId = this.patientService.getPatientId()();
    if (patientId != null) {
      this.getTreatmentByPatient(patientId);
    }
  });

  getTreatmentByPatient(patientId: number) {
    this.treatmentService.getTreatmentsByPatient(patientId).subscribe(
      (treatments: TreatmentDto[]) => {
        this.treatments = treatments;
      },
    );
  }

  toggleDetails(id: number) {
    this.moreDetails = !this.moreDetails;
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
