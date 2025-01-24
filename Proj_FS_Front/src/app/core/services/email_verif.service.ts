import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailVerificationService {

  private readonly apiUrl = 'http://localhost:8080/public/verify-email';

  constructor(private http: HttpClient) { }

  // Méthode pour vérifier si l'email existe
  verifyEmail(email: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}?email=${email}`);
  }
}
