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
  firstPatient: string | null = null;
  showPatients = false;
  @Input() isCollapsed: boolean = false;

    ngOnInit(): void {
    const userId = this.jwtService.getId() ?? 0; // Cambia por el ID real del usuario
    this.patientService.getPatientsByUser(userId).subscribe(patients => {
      console.log('Pacientes:', patients);
      this.patientsByUser = patients;
      if (patients.length > 0) {
        this.firstPatient = patients[0].name;
      }
    });
  }

  togglePacientes() {
  this.showPatients = !this.showPatients;
}

seleccionarPaciente(paciente: string) {
  this.firstPatient = paciente;
  this.showPatients = false; // cerrar después de seleccionar
}

}
