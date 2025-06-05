import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MedicamentDto } from '../interfaces/medicament-dto.interface';
import { HttpClient } from '@angular/common/http';
import { ALL_MEDICAMENTS_ENDPOINT } from '../../../config';

@Injectable({providedIn: 'root'})
export class MedicamentService {

  private http = inject(HttpClient);

  getAllMedicaments(): Observable<MedicamentDto[]> {
    return this.http.get<MedicamentDto[]>(ALL_MEDICAMENTS_ENDPOINT);
  }

}
