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

  constructor(private centerService: VaccinationCenterService) {}

  ngOnInit(): void {
    this.centerService.centers$.subscribe((data) => {
      this.centers = data;
    });
    this.centerService.loadCenters();
  }

  deleteCenter(id: number): void {
    // TODO : Ajouter la logique de suppression via API si nécessaire
    this.centers = this.centers.filter(center => center.id !== id);
    this.centerService.updateCenters(this.centers);
  }

  editCenter(id: number): void {
    // Il faut encore ajouter la navigation vers une page d'édition ou afficher un formulaire modifiable
    console.log(`Modification du centre avec ID: ${id}`);
  }

}
