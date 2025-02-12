
/**
 * Service pour gérer les opérations liées aux réservations.
 * Ce service fournit des méthodes pour valider et annuler des réservations.
 * 
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { Patient } from '../models/patients.model';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private baseUrl = `http://localhost:8080/public/centre`; // Base URL de l'API

  constructor(private http: HttpClient) {}

  // Fonction pour booker une reservation
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
}
