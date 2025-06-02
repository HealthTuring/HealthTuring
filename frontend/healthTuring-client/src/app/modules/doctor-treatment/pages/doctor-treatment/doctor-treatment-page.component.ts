import { Component, ViewChild } from '@angular/core';
import { SelectPatientsTreatmentComponent } from '../../components/select-patients-treatment/select-patients-treatment.component';
import { TreatmentFormComponent } from '../../components/treatment-form/treatment-form.component';
import { TreatmentTableComponent } from '../../components/treatment-table/treatment-table.component';
import { TreatmentDto } from '../../../treatment/interfaces/treatment-dto.interface';

@Component({
  imports: [TreatmentFormComponent, SelectPatientsTreatmentComponent, TreatmentTableComponent],
  templateUrl: './doctor-treatment-page.component.html',
  styleUrl: './doctor-treatment-page.component.css'
})
export class DoctorTreatmentPageComponent {

  @ViewChild(TreatmentTableComponent) treatmentTable?: TreatmentTableComponent;

  showTreatmentModal = false;
  selectedTreatment: TreatmentDto | null = null;

  openTreatmentModal() {
    this.selectedTreatment = null;
    this.showTreatmentModal = true;
  }

  onEditTreatment(treatment: TreatmentDto) {
    this.selectedTreatment = treatment;
    this.showTreatmentModal = true;
  }

  closeTreatmentModal() {
    this.showTreatmentModal = false;
    this.selectedTreatment = null;
  }

  onTreatmentUpdated() {
    this.treatmentTable?.refreshTreatments();
    this.closeTreatmentModal();
  }

  onTreatmentCreated() {
    this.treatmentTable?.refreshTreatments();
    this.closeTreatmentModal();
  }

}
