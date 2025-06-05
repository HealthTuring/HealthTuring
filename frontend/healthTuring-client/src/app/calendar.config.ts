import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { EnvironmentProviders, importProvidersFrom } from '@angular/core';

export function provideCalendar(): EnvironmentProviders {
  return importProvidersFrom(
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    })
  );
}
