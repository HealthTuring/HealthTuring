import { CommonModule } from '@angular/common';
import { Component, effect, inject } from '@angular/core';
import { PatientService } from '../../../../shared/services/patient.service';
import { TreatmentCardComponent } from '../../component/treatment-card/treatment-card.component';
import { TreatmentDto } from '../../interfaces/treatment-dto.interface';
import { TreatmentService } from '../../services/treatment.service';

@Component({
  imports: [CommonModule, TreatmentCardComponent],
  templateUrl: './treatment-page.component.html',
  styleUrl: './treatment-page.component.css',
})
export class TreatmentPageComponent {

  private treatmentService = inject(TreatmentService);
  private patientService = inject(PatientService);

  treatments: TreatmentDto[] = [];

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

}
