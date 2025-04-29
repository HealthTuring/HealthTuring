import { Routes } from "@angular/router";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { RegisterPageComponent } from "./pages/register-page/register-page.component";
import { AuthLayoutComponent } from "./layout/auth-layout/auth-layout.component";
import { AuthReverseGuard } from "./guards/auth-reverse.guard";

export const authRoutes: Routes = [
  {
    path: '',
    component: AuthLayoutComponent,
    children: [
      {
        path: 'login',
        component: LoginPageComponent,
        canActivate: [AuthReverseGuard]
      },
      {
        path: 'register',
        component: RegisterPageComponent,
        canActivate: [AuthReverseGuard]
      },
      {
        path: '**',
        redirectTo: 'login',
      },
    ],
  },
];

export default authRoutes;
