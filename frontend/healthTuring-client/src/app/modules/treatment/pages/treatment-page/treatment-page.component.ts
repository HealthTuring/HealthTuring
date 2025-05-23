import { Component, OnInit } from '@angular/core';
import { TRATAMIENTOS_MOCK } from '../../mocks/treatment.mock';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule],
  templateUrl: './treatment-page.component.html',
  styleUrl: './treatment-page.component.css'
})
export class TreatmentPageComponent implements OnInit {

  moreDetails: boolean[] = [];

    tratamientos: any[] = [];

  ngOnInit(): void {
    this.tratamientos = TRATAMIENTOS_MOCK.tratamientos;
    this.moreDetails = this.tratamientos.map(() => false)

  }

  toggleDetails(id: number) {
    this.moreDetails[id] = !this.moreDetails[id];
  }

}
