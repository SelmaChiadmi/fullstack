import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Employe } from '../models/employe.model';

@Injectable({
  providedIn: 'root', 
})

export class CreateMedecinService {
  private apiUrl = 'http://localhost:8080/admin/centre/medecins/create'; 
  

  constructor(private http: HttpClient) {}

createMedecin(medecinData: Employe): Observable<any> {
    const token = localStorage.getItem('jwt');
            
    if (!token) {
        console.error('Token manquant');
        return throwError(() => new Error('Token d’authentification manquant'));
    }
            
    console.log('Données envoyées dans la requête HTTP:', medecinData); //OK bonnes données
    return this.http.post<any>(`${this.apiUrl}`, medecinData, {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        })
    });
  }
}

