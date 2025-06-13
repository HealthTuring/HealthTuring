import { Routes } from "@angular/router";
import { HomeLayoutComponent } from "./layout/home-layout/home-layout.component";
import { TreatmentPageComponent } from "./treatment/pages/treatment-page/treatment-page.component";
import { CalendarPageComponent } from "./calendar/pages/calendar-page/calendar-page.component";
import { ChatComponent } from "./chat/components/chat/chat.component";
import { DoctorTreatmentPageComponent } from "./doctor-treatment/pages/doctor-treatment/doctor-treatment-page.component";
import { UserRoleGuard } from "../core/guards/user-role.guard";
import { DoctorRoleGuard } from "../core/guards/doctor-role.guard";
import { AppointmentPageComponent } from "./appointments/pages/appointment-page/appointment-page.component";
import { ProfilePatientPageComponent } from "./patient-profile/pages/profile-patient-page/profile-patient-page.component";

export const modulesRoutes: Routes = [
  {
    path: '',
    component: HomeLayoutComponent,
    children: [
      {
        path: 'treatments',
        component: TreatmentPageComponent,
        canActivate: [UserRoleGuard]
      },
      {
        path: 'calendar',
        component: CalendarPageComponent
      },
      {
        path: 'chat',
        component: ChatComponent
      },
      {
        path: 'appointment',
        component: AppointmentPageComponent
      },
      {
        path: 'patient-profile',
        component: ProfilePatientPageComponent
      },
      {
        path: 'doctor-treatment',
        component: DoctorTreatmentPageComponent,
        canActivate: [DoctorRoleGuard]
      },
      {
        path: '**',
        redirectTo: 'calendar',
        pathMatch: 'full'
      }
    ]
  },
];

export default modulesRoutes;
