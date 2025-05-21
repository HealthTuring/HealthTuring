import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SelectPatientsComponent } from '../select-patients/select-patients.component';

@Component({
  selector: 'shared-sidebar',
  imports: [CommonModule, SelectPatientsComponent],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  isCollapsed = true;

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

}
