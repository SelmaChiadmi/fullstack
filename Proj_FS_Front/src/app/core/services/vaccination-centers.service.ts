// vaccination-center.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { VaccinationCenter } from '../models/vaccination-centers.model';

@Injectable({
  providedIn: 'root' // Permet de rendre ce service accessible globalement
})
export class VaccinationCenterService {
  // Stockage des centres dans un BehaviorSubject, pour émettre les valeurs dans toute l'application
  private centers = new BehaviorSubject<VaccinationCenter[]>([
    { id: 1, nom: "Hôpital de Châtelet", ville: "Paris", creneaux: ["9h-12h", "14h-17h"] },
    { id: 2, nom: "Centre de Santé Dunois", ville: "Orléans", creneaux: ["10h-12h", "14h-16h"] },
    { id: 3, nom: "Clinique du Parc", ville: "Lyon", creneaux: ["8h-12h", "14h-18h"] },
    { id: 4, nom: "Centre Médical Pasteur", ville: "Marseille", creneaux: ["9h-13h", "14h-18h"] },
    { id: 5, nom: "Hôpital Saint-Jacques", ville: "Paris", creneaux: ["8h-12h"] },
    { id: 6, nom: "Centre Hospitalier Universitaire", ville: "Bordeaux", creneaux: ["8h-12h"] },
    { id: 7, nom: "Clinique Saint-Jean", ville: "Toulouse", creneaux: ["9h-12h", "14h-17h"] },
    { id: 8, nom: "Centre de Vaccination Voltaire", ville: "Strasbourg", creneaux: ["10h-12h", "14h-16h"] },
  ]);

  // Un getter pour récupérer l'observable des centres
  get centers$() {
    return this.centers.asObservable();
  }

  // Fonction pour mettre à jour les centres (si nécessaire)
  updateCenters(centers: VaccinationCenter[]) {
    this.centers.next(centers);
  }
}
