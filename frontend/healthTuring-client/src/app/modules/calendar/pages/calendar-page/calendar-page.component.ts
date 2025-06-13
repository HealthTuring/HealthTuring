import { Component, inject } from '@angular/core';
import { CalendarWrapperModule } from "../../../../calendar-wrapper.module";
import { JwtService } from '../../../../core/services/jwt.service';

@Component({
  imports: [CalendarWrapperModule],
  templateUrl: './calendar-page.component.html',
  styleUrl: './calendar-page.component.css'
})
export class CalendarPageComponent {
  jwtService = inject(JwtService);
}
