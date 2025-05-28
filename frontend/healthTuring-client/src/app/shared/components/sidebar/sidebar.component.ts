import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { SelectPatientsComponent } from '../select-patients/select-patients.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { JwtService } from '../../../core/services/jwt.service';

@Component({
  selector: 'shared-sidebar',
  imports: [CommonModule, SelectPatientsComponent, RouterLink, RouterLinkActive],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {

  private jwtService = inject(JwtService);

  isCollapsed = true;
  isDoctor = false;

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

  ngOnInit(): void {
      if (this.jwtService.getRole() === 'ROLE_DOC') {
        this.isDoctor = true;
      }
  }



}
