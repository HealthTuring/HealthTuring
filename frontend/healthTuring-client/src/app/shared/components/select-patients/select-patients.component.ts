import { Component, inject, Input } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { JwtService } from '../../../core/services/jwt.service';
import { PatientDto } from '../../interfaces/patient-dto.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'shared-select-patients',
  imports: [CommonModule],
  templateUrl: './select-patients.component.html',
  styleUrl: './select-patients.component.css'
})
export class SelectPatientsComponent {

  private patientService = inject(PatientService);
  private jwtService = inject(JwtService);

  patientsByUser: PatientDto[] = [];
  selectedPatient: PatientDto | null = null;
  showPatients = false;

  @Input() isCollapsed: boolean = false;

  ngOnInit(): void {
    const userId = this.jwtService.getId() ?? 0;
    this.patientService.getPatientsByUser(userId).subscribe(patients => {
      this.patientsByUser = patients;
      if (patients.length > 0) {
        this.selectPatient(patients[0]);
      }
    });
  }

  togglePacientes() {
    this.showPatients = !this.showPatients;
  }

  seleccionarPaciente(patient: PatientDto) {
    this.selectPatient(patient);
    this.showPatients = false;
  }

  private selectPatient(patient: PatientDto) {
    this.selectedPatient = patient;
    this.patientService.setPatientId(patient.id);
  }

  getInitials(fullName: string): string {
    if (!fullName) return '';
    return fullName
      .split(' ')
      .map(w => w[0])
      .join('')
      .slice(0, 2)
      .toUpperCase();
  }

}