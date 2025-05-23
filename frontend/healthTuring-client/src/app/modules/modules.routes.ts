import { Routes } from "@angular/router";
import { HomeLayoutComponent } from "./layout/home-layout/home-layout.component";
import { TreatmentPageComponent } from "./treatment/pages/treatment-page/treatment-page.component";

export const modulesRoutes: Routes = [
  {
    path: '',
    component: HomeLayoutComponent,
    children: [
      {
        path: 'treatments',
        component: TreatmentPageComponent
      }
    ]
  },
];

export default modulesRoutes;
