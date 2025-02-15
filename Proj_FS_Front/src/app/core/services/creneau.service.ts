import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

/*
    * Service pour gérer les créneaux de vaccination.
    @remarks Ce service utilise HttpClient pour envoyer des requêtes HTTP au backend afin de récupérer les créneaux disponibles pour un centre de vaccination à une date donnée.
*/
@Injectable({
  providedIn: 'root',
})
export class CreneauService {
  private baseUrl = 'http://localhost:8080/public/centre'; // URL de base pour accéder aux endpoints backend

  constructor(private http: HttpClient) {}

  // Récupère les créneaux disponibles pour un centre donné à une date précise
  getCreneaux(centerId: number, date: string): Observable<string[]> {
    const params = new HttpParams().set('date', date);
    return this.http.get<string[]>(`${this.baseUrl}/${centerId}/creneaux`, { params });
  }
}
