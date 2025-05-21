import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'shared-navbar',
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

    mostrarPacientes = false;

  pacientes = ['Cristiano Ronaldo', 'David Larrubia', 'Kylian Mbappé'];

  pacienteSeleccionado = 'Cristiano Ronaldo';

menuAbierto = false;


toggleMenu() {
  this.menuAbierto = !this.menuAbierto;
}

togglePacientes() {
  this.mostrarPacientes = !this.mostrarPacientes;
}

seleccionarPaciente(paciente: string) {
  this.pacienteSeleccionado = paciente;
  this.mostrarPacientes = false; // cerrar después de seleccionar
}

}
