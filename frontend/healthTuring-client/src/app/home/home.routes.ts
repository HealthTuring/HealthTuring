import { Routes } from "@angular/router";
import { HomePageComponent } from "./pages/home-page/home-page.component";

export const homeRoutes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },
];

export default homeRoutes;
