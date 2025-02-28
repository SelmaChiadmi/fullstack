/**
 * @file vaccination-centers.service.ts
 * @description Service Angular pour gérer les centres de vaccination.
 * @module VaccinationCenterService
 */

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { VaccinationCenter, VaccinationCenterDto } from '../models/vaccination-centers.model';
import { UpdateCentreDto } from '../models/updatecentre.model';

/**
 * @class VaccinationCenterService
 * @description Service pour gérer les centres de vaccination.
 */
@Injectable({
  providedIn: 'root',
})
export class VaccinationCenterService {
  /**
   * @property {string} apiUrl - URL de l'API pour récupérer les centres de vaccination.
   * @private
   */

  private readonly apiUrl = 'http://localhost:8080/public/centres';

  /**
   * @property {BehaviorSubject<VaccinationCenter[]>} centers - Stockage des données des centres de vaccination.
   * @private
   */

  private centers = new BehaviorSubject<VaccinationCenter[]>([]);

  /**
   * @constructor
   * @param {HttpClient} http - Client HTTP pour effectuer les requêtes API.
   */

  constructor(private http: HttpClient) {}

  /**
   * @method centers$
   * @description Getter pour obtenir l'observable des centres de vaccination.
   * @returns {Observable<VaccinationCenter[]>} Observable des centres de vaccination.
   */

  get centers$(): Observable<VaccinationCenter[]> {
    return this.centers.asObservable();

  }

  /**
   * @method loadCenters
   * @description Fonction pour charger les centres de vaccination depuis l'API.
   * @returns {void}
   */

  loadCenters(): void {
    this.http.get<VaccinationCenter[]>(this.apiUrl).subscribe(
      (data) => {
        this.centers.next(data); 
      },
      (error) => {
        console.error('Erreur lors du chargement des centres :', error);
      }
    );
  }

  /**
   * @method updateCenters
   * @description Méthode pour mettre à jour les centres de vaccination manuellement si nécessaire.
   * @param {VaccinationCenter[]} centers - Liste des centres de vaccination à mettre à jour.
   * @returns {void}
   */

  updateCenters(centers: VaccinationCenter[]): void {
    this.centers.next(centers);
  }

  // vaccination-centers.service.ts
  getCenterById(id: string): Observable<VaccinationCenter> {
    return this.http.get<VaccinationCenter>(`public/centres/${id}`);
  }


  updateCenter(centreId: number, centre: UpdateCentreDto): Observable<number>  {
    const token = localStorage.getItem('jwt');
    
    if (!token) {
      console.error('Token manquant');
      return throwError(() => new Error('Token d’authentification manquant'));
    }
  

  
    return this.http.put<number>(`http://localhost:8080/admin/centre/${centreId}/modify`,centre, { headers :new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('jwt')}`
    }) });
  }

  addCenter(center: VaccinationCenterDto): Observable<number> {
    const token = localStorage.getItem('jwt'); // Récupérer le token JWT depuis le localStorage

    if (!token) {
      console.error('Token manquant');
      return throwError(() => new Error('Token d’authentification manquant'));
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Ajout du token dans l'en-tête

    // Envoie de la requête POST avec le centre dans le corps de la requête
    return this.http.post<number>('http://localhost:8080/admin/centre/new', center, { headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('jwt')}`
    }) });
  }
}