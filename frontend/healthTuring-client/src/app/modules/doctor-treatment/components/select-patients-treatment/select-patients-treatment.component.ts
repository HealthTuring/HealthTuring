import { Component, inject, Input } from '@angular/core';
import { PatientDto } from '../../../../shared/interfaces/patient-dto.interface';
import { JwtService } from '../../../../core/services/jwt.service';
import { PatientService } from '../../../../shared/services/patient.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'select-patients-treatment',
  imports: [CommonModule],
  templateUrl: './select-patients-treatment.component.html',
  styleUrl: './select-patients-treatment.component.css'
})
export class SelectPatientsTreatmentComponent {

  private patientService = inject(PatientService);
  private jwtService = inject(JwtService);

  patientsByUser: PatientDto[] = [];
  selectedPatient: PatientDto | null = null;
  dropdownOpen = false;

  @Input() isCollapsed: boolean = false;

  ngOnInit(): void {
    const doctorId = this.jwtService.getId() ?? 0;
    console.log(doctorId)
    this.patientService.getPatientsByDoctor(doctorId).subscribe(patients => {
      this.patientsByUser = patients;
      if (patients.length > 0) {
        this.selectPatient(patients[0]);
      }
    });
  }

  seleccionarPaciente(patient: PatientDto) {
    this.selectPatient(patient);
    this.dropdownOpen = false;
  }

  private selectPatient(patient: PatientDto) {
    this.selectedPatient = patient;
    this.patientService.setPatient(patient);
    this.patientService.setPatientId(patient.id);
    this.dropdownOpen = false;
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

}
