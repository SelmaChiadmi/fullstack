import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Patient } from "../models/patients.model";
import { Observable, throwError } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private baseUrl = `http://localhost:8080/public/centre`; // Base URL de l'API

  constructor(private http: HttpClient) {}

  // Fonction pour booker une réservation
  bookAppointment(centreId: number, date: string, heure: string, patient: Patient): Observable<any> {
    const url = `${this.baseUrl}/${centreId}/bookings?date=${date}&heure=${heure}`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(url, patient, { headers });
  }

  // Fonction pour annuler une réservation
  cancelReservation(bookingId: number): Observable<any> {
    const url = `${this.baseUrl}/booking/${bookingId}`;
    return this.http.delete(url);
  }

  // Fonction pour mettre à jour le statut de validation de la réservation
  updateReservationValidation(reservationId: number, isValidated: boolean): Observable<any> {
    const token = localStorage.getItem('jwt');  // Récupération du token d'authentification
    
    if (!token) {
      console.error('Token manquant');
      return throwError(() => new Error('Token manquant'));
    }
      
    const url = `http://localhost:8080/admin/planning/reservation/${reservationId}?isValidated=${isValidated}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`  
    });
    return this.http.patch(url, {}, { headers }); 

  }
}
