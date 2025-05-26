import { CalendarDateFormatter, DateFormatterParams } from 'angular-calendar';
import { formatDate } from '@angular/common';
import { Injectable } from '@angular/core';
import { es } from 'date-fns/locale/es';

@Injectable()
export class CustomDateFormatter extends CalendarDateFormatter {
  // you can override any of the methods defined in the parent class

  public override monthViewColumnHeader({ date }: DateFormatterParams): string {
    return formatDate(date, 'EEE', 'es');
  }

/*   public override monthViewTitle({ date }: DateFormatterParams): string {
    return formatDate(date, 'MMM y', 'es');
  } */

/*   public override weekViewColumnHeader({ date }: DateFormatterParams): string {
    return formatDate(date, 'EEE', 'es');
  } */

/*   public override dayViewHour({ date }: DateFormatterParams): string {
    return formatDate(date, 'HH:mm', 'es');
  } */
}
