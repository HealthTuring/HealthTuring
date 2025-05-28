import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { PatientService } from '../../../../shared/services/patient.service';
import { JwtService } from '../../../../core/services/jwt.service';
import { PatientDto } from '../../../../shared/interfaces/patient-dto.interface';

@Component({
  selector: 'doctor-select-patient',
  imports: [CommonModule],
  templateUrl: './select-patient.component.html',
  styleUrl: './select-patient.component.css'
})
export class SelectPatientComponent {

  private patientService = inject(PatientService);
  private jwtService = inject(JwtService);

  patientsByDoctor: PatientDto[] = [];
  selectedPatient: PatientDto | null = null;
  dropdownOpen = false;

  ngOnInit(): void {
    const doctorId = this.jwtService.getId() ?? 0;
    this.patientService.getPatientsByDoctor(doctorId).subscribe(patients => {
      this.patientsByDoctor = patients;
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
    this.patientService.setPatientId(patient.id);
    this.dropdownOpen = false;
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

}