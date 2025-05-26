import { Routes } from "@angular/router";
import { HomeLayoutComponent } from "./layout/home-layout/home-layout.component";
import { TreatmentPageComponent } from "./treatment/pages/treatment-page/treatment-page.component";
import { CalendarPageComponent } from "./calendar/pages/calendar-page/calendar-page.component";

export const modulesRoutes: Routes = [
  {
    path: '',
    component: HomeLayoutComponent,
    children: [
      {
        path: 'treatments',
        component: TreatmentPageComponent
      },
      {
        path: 'calendar',
        component: CalendarPageComponent
      }
    ]
  },
];

export default modulesRoutes;
