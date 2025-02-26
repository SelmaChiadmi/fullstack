import { Component } from '@angular/core';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-centres',
  standalone: true,
  imports: [CommonModule],
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


  editCenter(id: number): void {
    // Il faut encore ajouter la navigation vers une page d'édition ou afficher un formulaire modifiable
    console.log(`Modification du centre avec ID: ${id}`);
  }

}
