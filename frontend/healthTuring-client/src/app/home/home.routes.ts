import { Routes } from "@angular/router";
import { HomePageComponent } from "./layout/home-layout/home-page.component";

export const homeRoutes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },
];

export default homeRoutes;
