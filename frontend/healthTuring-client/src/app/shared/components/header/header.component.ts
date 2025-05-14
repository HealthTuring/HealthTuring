import { Component } from '@angular/core';
import { ProfileMenuComponent } from '../profile-menu/profile-menu.component';

@Component({
  selector: 'shared-header',
  imports: [ProfileMenuComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {


}
