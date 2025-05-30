import { Component, inject, Input } from '@angular/core';
import { TreatmentDto } from '../../interfaces/treatment-dto.interface';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'treatment-card',
  imports: [CommonModule],
  templateUrl: './treatment-card.component.html',
  styleUrl: './treatment-card.component.css',
  providers: [DatePipe]
})
export class TreatmentCardComponent {

  private datePipe = inject(DatePipe);

  @Input() treatment!: TreatmentDto;
  moreDetails: boolean = false;

  toggleDetails(id: number) {
    this.moreDetails = !this.moreDetails;
  }

  formatDate(dateStr: Date | null): string {
    if (!dateStr) return 'Indefinido';
    return this.datePipe.transform(dateStr, 'dd/MM/yyyy', 'UTC') || '';
  }

  formatIncompatibilities(incompatibilities: any[]): string {
    if (!Array.isArray(incompatibilities) || incompatibilities.length === 0) {
      return 'Ninguna';
    }
    return incompatibilities.map(i => i.incompatibleMedication || i.incompatibleSubstance).filter(Boolean).join(', ');
  }

}
