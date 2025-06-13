import { Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from '../../../shared/components/header/header.component';
import { SidebarComponent } from '../../../shared/components/sidebar/sidebar.component';
import { RouterOutlet } from '@angular/router';
import { JwtService } from '../../../core/services/jwt.service';
import { DoctorSidebarComponent } from '../../../shared/components/doctor-sidebar/doctor-sidebar.component';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule, HeaderComponent, SidebarComponent, RouterOutlet, DoctorSidebarComponent],
  templateUrl: './home-layout.component.html',
  styleUrl: './home-layout.component.css'
})
export class HomeLayoutComponent implements OnInit {

  private jwtService = inject(JwtService);

  isCollapsed = true;
  isDoctor = false;
  isMobile = false;
  showSidebar = true;


  ngOnInit(): void {
    if (this.jwtService.getRole() === 'ROLE_DOC') {
      this.isDoctor = true;
    }
    this.checkScreenSize();
    window.addEventListener('resize', this.checkScreenSize.bind(this));

  }

  checkScreenSize() {
    this.isMobile = window.innerWidth < 768;
    this.showSidebar = !this.isMobile;
  }

  toggleSidebar() {
    this.showSidebar = !this.showSidebar;
  }

}
