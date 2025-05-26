import { Component, effect, inject } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { addMonths, format, startOfDay, subMonths } from 'date-fns';
import { TreatmentService } from '../../../treatment/services/treatment.service';
import { PatientService } from '../../../../shared/services/patient.service';
import { TreatmentDto } from '../../../treatment/interfaces/treatment-dto.interface';
import { es } from 'date-fns/locale';

@Component({
  selector: 'calendar-component',
  standalone: false,
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {

    locale: string = 'es';

  private treatmentService = inject(TreatmentService);
  private patientService = inject(PatientService);

  treatments: TreatmentDto[] = [];

  patientEffect = effect(() => {
    const patientId = this.patientService.getPatientId()();
    if (patientId != null) {
      this.getTreatmentByPatient(patientId);
    }
  });

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();

  events: CalendarEvent[] = [
    {
      start: startOfDay(new Date()),
      title: 'Evento inicial',
    },
  ];

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

  private getTreatmentByPatient(patientId: number) {
    this.treatmentService.getTreatmentsByPatient(patientId).subscribe(
      (treatments: TreatmentDto[]) => {
        this.treatments = treatments;
        this.events = this.mapTreatmentsToEvents(treatments);
      }
    );
  }

  private mapTreatmentsToEvents(treatments: TreatmentDto[]): CalendarEvent[] {
    return treatments.map((treatment) => ({
      title: treatment.nameMedication,
      start: new Date(treatment.startDate),
      end: treatment.endDate ? new Date(treatment.endDate) : undefined,
      allDay: true,
      meta: {
        description: treatment.description,
        frequency: treatment.frequency,
        duration: treatment.duration,
      },
    }));
  }
}