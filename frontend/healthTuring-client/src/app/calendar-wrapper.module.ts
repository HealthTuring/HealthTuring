// src/app/calendar-wrapper.module.ts
import { NgModule } from '@angular/core';
import localeEs from '@angular/common/locales/es';
import { CommonModule, registerLocaleData } from '@angular/common';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CalendarComponent } from './modules/calendar/components/calendar/calendar.component';

registerLocaleData(localeEs);

@NgModule({
  declarations: [CalendarComponent],
  imports: [
    CommonModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  exports: [CalendarComponent],
})
export class CalendarWrapperModule {}
