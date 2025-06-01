import { Component } from '@angular/core';
import { SelectPatientsTreatmentComponent } from '../../components/select-patients-treatment/select-patients-treatment.component';
import { TreatmentFormComponent } from '../../components/treatment-form/treatment-form.component';
import { TreatmentTableComponent } from '../../components/treatment-table/treatment-table.component';

@Component({
  imports: [TreatmentFormComponent, SelectPatientsTreatmentComponent, TreatmentTableComponent],
  templateUrl: './doctor-treatment-page.component.html',
  styleUrl: './doctor-treatment-page.component.css'
})
export class DoctorTreatmentPageComponent {

  showTreatmentModal = false;

  openTreatmentModal() {
    this.showTreatmentModal = true;
  }
  closeTreatmentModal() {
    this.showTreatmentModal = false;
  }

}
