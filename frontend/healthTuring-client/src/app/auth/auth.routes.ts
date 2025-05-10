import { Routes } from "@angular/router";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { RegisterPageComponent } from "./pages/register-page/register-page.component";
import { AuthLayoutComponent } from "./layout/auth-layout/auth-layout.component";
import { AuthReverseGuard } from "./guards/auth-reverse.guard";
import { ForgetPasswordPageComponent } from "./pages/forget-password/forget-password-page.component";
import { ResetPasswordPageComponent } from "./pages/reset-password/reset-password-page.component";
import { ConfirmEmailPageComponent } from "./pages/confirm-email/confirm-email-page.component";

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
        path: 'forget-password',
        component: ForgetPasswordPageComponent,
        canActivate: [AuthReverseGuard]
      },
      {
        path: 'reset-password/:token',
        component: ResetPasswordPageComponent,
        canActivate: [AuthReverseGuard]
      },
      {
        path: 'confirm-email/:token',
        component: ConfirmEmailPageComponent,
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
