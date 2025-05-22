import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SelectPatientsComponent } from '../select-patients/select-patients.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'shared-sidebar',
  imports: [CommonModule, SelectPatientsComponent, RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  isCollapsed = true;

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

}
