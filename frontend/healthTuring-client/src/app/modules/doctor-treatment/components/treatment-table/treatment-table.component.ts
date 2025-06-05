import { CommonModule, DatePipe } from '@angular/common';
import { Component, effect, EventEmitter, inject, Input, Output } from '@angular/core';
import { TreatmentDto } from '../../../treatment/interfaces/treatment-dto.interface';
import { TreatmentService } from '../../../treatment/services/treatment.service';
import { PatientService } from '../../../../shared/services/patient.service';
import { DoctorService } from '../../services/doctor.service';
import { ConfirmModalComponent } from '../../../../shared/components/confirm-modal/confirm-modal.component';

@Component({
  selector: 'treatment-table',
  imports: [CommonModule, ConfirmModalComponent],
  templateUrl: './treatment-table.component.html',
  styleUrl: './treatment-table.component.css',
  providers: [DatePipe]
})
export class TreatmentTableComponent {

  private datePipe = inject(DatePipe);
  private treatmentService = inject(TreatmentService);
  private patientService = inject(PatientService);
  private doctorService = inject(DoctorService);

  @Output() editTreatment = new EventEmitter<TreatmentDto>();

  treatments: TreatmentDto[] = [];

  moreDetails: boolean = false;
  showConfirm = false;
  treatmentToDelete: number | null = null;

  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  totalElements: number = 0;

  patientEffect = effect(() => {
    const patientId = this.patientService.getPatientId()();
    if (patientId != null) {
      this.getTreatmentsByPatientPaged(patientId);
    }
  });

getTreatmentsByPatientPaged(patientId: number) {
  this.treatmentService.getTreatmentsByPatientPaged(patientId, this.currentPage, this.pageSize).subscribe(
    (page) => {
      this.treatments = page.content;
      this.totalPages = page.totalPages;
      this.totalElements = page.totalElements;
    }
  );
}

goToPage(page: number) {
  this.currentPage = page;
  const patientId = this.patientService.getPatientId()();
  if (patientId != null) {
    this.getTreatmentsByPatientPaged(patientId);
  }
}

  toggleDetails() {
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

  refreshTreatments() {
    const patientId = this.patientService.getPatientId()();
    if (patientId != null) {
      this.getTreatmentsByPatientPaged(patientId);
    }
  }

  openDeleteModal(id: number) {
    this.treatmentToDelete = id;
    this.showConfirm = true;
  }

  closeDeleteModal() {
    this.showConfirm = false;
    this.treatmentToDelete = null;
  }

  confirmDelete() {
    if (this.treatmentToDelete !== null) {
      this.doctorService.deleteTreatment(this.treatmentToDelete).subscribe((ok) => {
        if (ok) {
          this.refreshTreatments();
        }
        this.closeDeleteModal();
      });
    }
  }


}
