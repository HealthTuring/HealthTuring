import { Component, effect, inject, Signal } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppointmentRequest } from '../../interfaces/appointment-request.interface';
import { PatientService } from '../../../../shared/services/patient.service';
import { PatientDto } from '../../../../shared/interfaces/patient-dto.interface';

@Component({
  selector: 'appointment-reserve',
  imports: [CommonModule, FormsModule],
  templateUrl: './appointment-reserve.component.html',
  styleUrl: './appointment-reserve.component.css'
})
export class AppointmentReserveComponent {

  private appointmentService = inject(AppointmentService);
  private patientService = inject(PatientService);

  doctorId: number = 0;
  patientId: number = 0;
  date: string = '';
  slots: string[] = [];
  selectedSlot: string = '';
  reason: string = '';
  errorMsg: string = '';
  minDate: string = '';
  maxDate: string = '';
  patientSignal: Signal<PatientDto | null> = this.patientService.getPatient();

  constructor() {
    const today = new Date();
    this.minDate = this.formatDateInput(today);
    const nextWeek = new Date(today);
    nextWeek.setDate(today.getDate() + 7);
    this.maxDate = this.formatDateInput(nextWeek);
  }

  patientEffect = effect(() => {
    const patient = this.patientSignal();
    if (patient != null) {
      this.patientId = patient.id;
      this.doctorId = patient.doctorId;
    }
  });

  onDateChange() {
    if (this.date && this.doctorId) {
      this.appointmentService.getAvailableSlots(this.doctorId, this.date).subscribe(
        (data) => {
          this.slots = data;
          this.selectedSlot = '';
          this.errorMsg = '';
        }
      );
    }
  }

  reserve() {
    if (!this.selectedSlot) {
      this.errorMsg = 'Selecciona un horario.';
      return;
    }
    const req: AppointmentRequest = {
      patientId: this.patientId,
      doctorId: this.doctorId,
      date: this.date,
      startTime: this.selectedSlot,
      reason: this.reason && this.reason.trim() ? this.reason.trim() : 'Sin motivo especificado'
    };
    this.appointmentService.reserveAppointment(req).subscribe({
      next: () => {
        this.errorMsg = '';
        this.selectedSlot = '';
        this.reason = '';
        this.slots = [];
        this.date = '';
      },
      error: err => {
        this.errorMsg = err?.error || 'No se pudo reservar la cita';
      }
    });
  }

  formatDateInput(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  formatDate(dateStr: string): string {
    if (!dateStr) return '';
    const [year, month, day] = dateStr.split('-');
    return `${day}/${month}/${year}`;
  }
}