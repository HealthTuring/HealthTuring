import { Component, effect, inject } from '@angular/core';
import { CalendarDateFormatter, CalendarEvent, CalendarView } from 'angular-calendar';
import { addMonths, format, subMonths } from 'date-fns';
import { TreatmentService } from '../../../treatment/services/treatment.service';
import { PatientService } from '../../../../shared/services/patient.service';
import { TreatmentDto } from '../../../treatment/interfaces/treatment-dto.interface';
import { es } from 'date-fns/locale';
import { AppointmentService } from '../../../appointments/services/appointment.service';
import { AppointmentDto } from '../../../appointments/interfaces/appointment-dto.interface';
import { CustomDateFormatter } from '../../custom-date-formatter.provider';

@Component({
  selector: 'calendar-component',
  standalone: false,
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css',
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter,
    },
  ]
})
export class CalendarComponent {

  locale: string = 'es';

  private treatmentService = inject(TreatmentService);
  private patientService = inject(PatientService);
  private appointmentService = inject(AppointmentService);

  treatments: TreatmentDto[] = [];
  appointments: AppointmentDto[] = [];

patientEffect = effect(() => {
  const patientId = this.patientService.getPatientId()();
  if (patientId != null) {
    this.clearData();
    this.loadData(patientId);
  } else {
    this.clearData();
  }
});

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();

  events: CalendarEvent[] = [];

  setView(view: CalendarView) {
    this.view = view;
  }

  previousPeriod() {
    this.viewDate = this.getModifiedDate(-1);
  }

  nextPeriod() {
    this.viewDate = this.getModifiedDate(1);
  }

  today() {
    this.viewDate = new Date();
  }

  private getModifiedDate(direction: number): Date {
    return direction > 0 ? addMonths(this.viewDate, 1) : subMonths(this.viewDate, 1);
  }

  get formattedViewDate(): string {
    return format(this.viewDate, 'MMMM yyyy', { locale: es });
  }

  private loadData(patientId: number) {
    this.treatmentService.getTreatmentsByPatient(patientId).subscribe((treatments) => {
      this.treatments = treatments;
      const treatmentEvents = this.mapTreatmentsToEvents(treatments);

      this.appointmentService.getAppointmentsByPatient(patientId).subscribe((appointments) => {
        this.appointments = appointments;
        const appointmentEvents = this.mapAppointmentsToEvents(appointments);

        this.events = [...treatmentEvents, ...appointmentEvents];
      });
    });
  }

  private mapTreatmentsToEvents(treatments: TreatmentDto[]): CalendarEvent[] {
    return treatments.map((treatment) => ({
      title: `💊 ${treatment.nameMedication}`,
      start: new Date(treatment.startDate),
      end: treatment.endDate ? new Date(treatment.endDate) : undefined,
      allDay: true,
      color: { primary: '#2DABB9', secondary: '#C0E6EA' },
      meta: {
        type: 'treatment',
        description: treatment.description,
        frequency: treatment.frequency,
        duration: treatment.duration,
      },
    }));
  }

  private mapAppointmentsToEvents(appointments: AppointmentDto[]): CalendarEvent[] {
    return appointments.map((appointment) => {
      const [startHour, startMinute] = appointment.startTime.split(':').map(Number);
      const [endHour, endMinute] = appointment.endTime.split(':').map(Number);
      const start = new Date(appointment.date);
      start.setHours(startHour, startMinute);

      const end = new Date(appointment.date);
      end.setHours(endHour, endMinute);

      return {
        title: `🩺 Cita: ${appointment.reason}`,
        start,
        end,
        allDay: false,
        color: { primary: '#ff5f0a', secondary: '#ffa779' },
        meta: {
          type: 'appointment',
          patientName: appointment.patientName,
        },
      };
    });
  }

  private clearData() {
  this.treatments = [];
  this.appointments = [];
  this.events = [];
}
}
