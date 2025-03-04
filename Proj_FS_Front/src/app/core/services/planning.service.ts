import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlanningService {

  private apiUrl = 'http://localhost:8080/admin/planning'; 

  constructor(private http: HttpClient) { }

  // Méthode pour obtenir les réservations par date
  getResaByDate(date: string): Observable<any> {
    const token = localStorage.getItem('jwt');  // Récupération du token d'authentification

    if (!token) {
      console.error('Token manquant');
      return throwError(() => new Error('Token manquant'));
    }

    const params = new HttpParams().set('date', date); // Paramètre 'date' à envoyer

    return this.http.get(this.apiUrl, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`  
      }),
      params: params  
    });
  }
}
