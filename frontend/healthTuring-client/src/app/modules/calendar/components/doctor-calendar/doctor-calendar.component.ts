import { Component, inject, OnInit } from '@angular/core';
import { CalendarDateFormatter, CalendarEvent, CalendarView } from 'angular-calendar';
import { addMonths, format, subMonths } from 'date-fns';
import { AppointmentDto } from '../../../appointments/interfaces/appointment-dto.interface';
import { CustomDateFormatter } from '../../custom-date-formatter.provider';
import { AppointmentService } from '../../../appointments/services/appointment.service';
import { JwtService } from '../../../../core/services/jwt.service';
import { es } from 'date-fns/locale';

@Component({
  selector: 'doctor-calendar',
  standalone: false,
  templateUrl: '../calendar/calendar.component.html',
  styleUrl: '../calendar/calendar.component.css',
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter,
    },
  ]
})
export class DoctorCalendarComponent implements OnInit {

  locale: string = 'es';

  private appointmentService = inject(AppointmentService);
  private jwtService = inject(JwtService);

  appointments: AppointmentDto[] = [];

  ngOnInit(): void {
    const doctorId = this.jwtService.getId();
    if (doctorId != null) {
      this.clearData();
      this.loadAppointments(doctorId);
    } else {
      this.clearData();
    }
  }

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

  private loadAppointments(doctorId: number) {
    this.appointmentService.getAppointmentsByDoctor(doctorId).subscribe((appointments) => {
      this.appointments = appointments;
      this.events = this.mapAppointmentsToEvents(appointments);
    });
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
        title: `🩺 Cita: ${appointment.reason} - ${appointment.patientName}`,
        start,
        end,
        allDay: false,
        color: { primary: '#0a75ad', secondary: '#d0e7f9' },
        meta: {
          type: 'appointment',
          patientName: appointment.patientName,
        },
      };
    });
  }

  private clearData() {
    this.appointments = [];
    this.events = [];
  }
}
