import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JourDispoService {

  private apiUrl: string = 'http://localhost:8080/public/centre';  

  constructor(private http: HttpClient) {}

  // Méthode pour récupérer les jours disponibles d'un centre
  getJoursDisponibles(centreId: number): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/${centreId}/jours`);
  }
}
