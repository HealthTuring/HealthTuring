import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';

import { routes } from './app.routes';

import { provideToastr } from 'ngx-toastr';
import { authTokenInterceptor } from './core/interceptors/auth-token.interceptor';
import { provideCalendar } from './calendar.config';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withFetch(),
      withInterceptors([
        authTokenInterceptor
      ])
    ),
    provideAnimations(),
    provideToastr({
      preventDuplicates: true,
      maxOpened: 1,
    }),
    provideCalendar(),
  ],
};
