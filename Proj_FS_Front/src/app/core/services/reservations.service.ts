import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Patient } from "../models/patients.model";
import { Observable } from "rxjs";
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
    const url = `http://localhost:8080/admin/planning/${reservationId}`;
    const body = { isValidated }; // Passer la valeur du statut validation dans le corps de la requête
    
    return this.http.patch(url, body);  
  }
}
