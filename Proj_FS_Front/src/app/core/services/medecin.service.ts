import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Employe } from '../models/employe.model';

@Injectable({
  providedIn: 'root', 
})

export class MedecinService {
  private apiUrl = 'http://localhost:8080/admin/centre/medecins'; 
  

  constructor(private http: HttpClient) {}

// création d'un médecin
createMedecin(medecinData: Employe): Observable<any> {
    const token = localStorage.getItem('jwt');
            
    if (!token) {
        console.error('Token manquant');
        return throwError(() => new Error('Token d’authentification manquant'));
    }
            
    console.log('Données envoyées dans la requête HTTP:', medecinData); //OK bonnes données
    return this.http.post<any>(`${this.apiUrl}/create`, medecinData, {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        })
    });
  }

// récupération de la liste des médecins
getMedecins(): Observable<any[]> {
  const token = localStorage.getItem('jwt');  // Récupération du token JWT stocké localement

  if (!token) {
    console.error('Token manquant');
    return throwError(() => new Error('Token d’authentification manquant'));
  }

  console.log('Requête pour récupérer la liste des médecins');
  return this.http.get<any[]>(`${this.apiUrl}`, {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    })
  });
}

deleteMedecin(email: string): Observable<any> {
  const token = localStorage.getItem('jwt');
  if (!token) {
    console.error('Token manquant');
    return throwError(() => new Error('Token manquant'));
  }

  return this.http.delete(`${this.apiUrl}/delete?email=${email}`, { 
    headers: new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
  }).pipe(
    catchError(error => {
      console.error(`Erreur lors de la suppression du médecin ${email}:`, error.message);
      return throwError(() => new Error(`Impossible de supprimer le médecin ${email}`));
    })
  );
}

}
