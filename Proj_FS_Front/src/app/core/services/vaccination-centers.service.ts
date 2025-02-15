/**
 * @file vaccination-centers.service.ts
 * @description Service Angular pour gérer les centres de vaccination.
 * @module VaccinationCenterService
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { VaccinationCenter } from '../models/vaccination-centers.model';

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
}
