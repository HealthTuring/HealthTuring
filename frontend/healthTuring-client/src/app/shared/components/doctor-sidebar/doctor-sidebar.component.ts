import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'shared-doctor-sidebar',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './doctor-sidebar.component.html',
  styleUrl: './doctor-sidebar.component.css'
})
export class DoctorSidebarComponent {

  isCollapsed = true;

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

}
