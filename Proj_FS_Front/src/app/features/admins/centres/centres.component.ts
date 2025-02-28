import { Component } from '@angular/core';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { CommonModule } from '@angular/common';
import { EditCenterComponent } from '../edit-center/edit-center.component';
import { AddCenterComponent } from "../add-center/add-center.component";

@Component({
  selector: 'app-centres',
  standalone: true,
  imports: [CommonModule, EditCenterComponent, AddCenterComponent],
  templateUrl: './centres.component.html',
  styleUrl: './centres.component.css'
})
export class CentresComponent {
  centers: VaccinationCenter[] = [];
  showNewCenterForm = false; // Variable pour afficher le formulaire

  constructor(private centerService: VaccinationCenterService) {}

  ngOnInit(): void {
    this.centerService.centers$.subscribe((data) => {
      this.centers = data;
    });
    this.centerService.loadCenters();
  }

  openNewCenterForm(): void {
    this.showNewCenterForm = true; // Active l'affichage du formulaire
  }


  selectedCenter: VaccinationCenter | null = null;

  editCenter(center: VaccinationCenter): void {
    this.selectedCenter = { ...center }; 
  }
  
  refreshCenters(): void {
    this.selectedCenter = null; // Ferme le formulaire après mise à jour
    this.centerService.loadCenters(); // Recharge la liste
    this.showNewCenterForm = false; // Ferme le formulaire d'ajout
  }

}
