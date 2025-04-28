import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HttpClientModule], // <-- aquí importa HttpClientModule
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  private http: HttpClient = inject(HttpClient);
  title = 'healthTuring-client';

  mensaje = '';

  constructor() {
    this.http.get('http://localhost:8080/api/hola', { responseType: 'text' })
      .subscribe(res => {
        this.mensaje = res;
      });
  }
}
