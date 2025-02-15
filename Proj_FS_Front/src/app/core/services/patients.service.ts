import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../models/patients.model';
@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiUrl = 'http://localhost:8080/public/patients/create-patient'; 

  constructor(private http: HttpClient) {}

  createPatient(patientData: Patient): Observable<any> {
    console.log('Données envoyées dans la requête HTTP:', patientData); //OK bonnes données
    return this.http.post<any>(`${this.apiUrl}`, patientData);
  }
}