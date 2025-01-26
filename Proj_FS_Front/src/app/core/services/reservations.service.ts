
/**
 * Service pour gérer les opérations liées aux réservations.
 * Ce service fournit des méthodes pour valider et annuler des réservations.
 * 
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private baseUrl = `http://localhost:8080/public/centre`; // Base URL de l'API

  constructor(private http: HttpClient) {}

  // Fonction pour valider une réservation
  validateReservation(reservationId: number, isValidated: boolean): Observable<any> {
    const url = `${this.baseUrl}/admin/update-validation/${reservationId}`;
    return this.http.patch(url, null, {
      params: { isValidated: isValidated.toString() },
    });
  }

  // Fonction pour annuler une réservation
  cancelReservation(bookingId: number): Observable<any> {
    const url = `${this.baseUrl}/booking/${bookingId}`;
    return this.http.delete(url);
  }
}
