import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employe } from '../models/employe.model';

@Injectable({
  providedIn: 'root'
})
export class SuperAdminService {
  private apiUrl = 'http://localhost:8080/admin/config';  // URL de votre API

  constructor(private http: HttpClient) {}

  // Fonction pour récupérer la liste des super administrateurs
  getSuperAdmins(): Observable<any[]> {
    const token = localStorage.getItem('jwt');  // Récupération du token JWT stocké localement

    if (!token) {
      console.error('Token manquant');
      return new Observable<any[]>();  // Retourne une observable vide si le token est manquant
    }

    return this.http.get<any[]>(this.apiUrl, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    });
  }

   // Fonction pour créer un super administrateur
   createSuperAdmin(superadmindto: Employe, centreId: number): Observable<any> {
    const token = localStorage.getItem('jwt');  // Récupération du token JWT stocké localement

    if (!token) {
      console.error('Token manquant');
      return new Observable<any>();  // Retourne une observable vide si le token est manquant
    }

    return this.http.post<any>(`${this.apiUrl}/create/${centreId}`, superadmindto, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      })
    });
  }

  // Fonction pour supprimer un super administrateur
  deleteSuperAdmin(superAdminMail : String ): Observable<void> {
    const token = localStorage.getItem('jwt');  // Récupération du token JWT stocké localement

    if (!token) {
      console.error('Token manquant');
      return new Observable<void>();  // Retourne une observable vide si le token est manquant
    }

    return this.http.delete<void>(`${this.apiUrl}/delete/${superAdminMail}`, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    });
}
}
