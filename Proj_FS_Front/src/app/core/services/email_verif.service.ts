import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



/**
 * Service pour la vérification des emails.
 * 
 * @remarks
 * Ce service utilise HttpClient pour envoyer des requêtes HTTP au backend afin de vérifier si un email existe dans la base de données.
 * 
 * @example
 * ```typescript
 * constructor(private emailVerificationService: EmailVerificationService) {}
 * 
 * this.emailVerificationService.verifyEmail('example@example.com').subscribe(
 *   (exists: boolean) => {
 *     if (exists) {
 *       console.log('Email exists');
 *     } else {
 *       console.log('Email does not exist');
 *     }
 *   }
 * );
 * ```
 */


@Injectable({
  providedIn: 'root'
})
export class EmailVerificationService {

  /**
   * URL de l'API pour la vérification des emails.
   */
  private readonly apiUrl = 'http://localhost:8080/public/verify-email';

  /**
   * Constructeur du service EmailVerificationService.
   * 
   * @param http - Instance de HttpClient pour effectuer des requêtes HTTP.
   */
  constructor(private http: HttpClient) { }

  /**
   * Vérifie si un email existe.
   * 
   * @param email - L'email à vérifier.
   * @returns Un Observable qui émet une valeur booléenne indiquant si l'email existe.
   */
  verifyEmail(email: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}?email=${email}`);
  }
}
