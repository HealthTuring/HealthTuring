import { Component, OnInit } from '@angular/core';
import { TRATAMIENTOS_MOCK } from '../../mocks/treatment.mock';

@Component({
  imports: [],
  templateUrl: './treatment-page.component.html',
  styleUrl: './treatment-page.component.css'
})
export class TreatmentPageComponent implements OnInit {

    tratamientos: any[] = [];

  ngOnInit(): void {
    this.tratamientos = TRATAMIENTOS_MOCK.tratamientos;
  }

}
