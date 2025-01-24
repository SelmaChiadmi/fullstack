import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { VaccinationCenter } from '../models/vaccination-centers.model';

@Injectable({
  providedIn: 'root',
})
export class VaccinationCenterService {
  private readonly apiUrl = 'http://localhost:8080/public/centres'; // URL de ton API
  private centers = new BehaviorSubject<VaccinationCenter[]>([]); // Stockage des données

  constructor(private http: HttpClient) {}

  // Getter pour obtenir l'observable des centres
  get centers$(): Observable<VaccinationCenter[]> {
    return this.centers.asObservable();
  }

  // Fonction pour charger les centres depuis l'API
  loadCenters() {
    this.http.get<VaccinationCenter[]>(this.apiUrl).subscribe(
      (data) => {
        this.centers.next(data); // Met à jour le BehaviorSubject
      },
      (error) => {
        console.error('Erreur lors du chargement des centres :', error);
      }
    );
  }

  // Méthode pour mettre à jour les centres manuellement si nécessaire
  updateCenters(centers: VaccinationCenter[]) {
    this.centers.next(centers);
  }
}
