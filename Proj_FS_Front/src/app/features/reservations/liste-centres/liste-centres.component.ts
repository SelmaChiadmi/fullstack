import { Component, OnInit,EventEmitter,Output } from '@angular/core';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Location } from '@angular/common';
import { EmitterService } from '../../../core/services/emitter.service'; // Ensure this path is correct

@Component({
  selector: 'app-liste-centres',
  standalone: true,
  providers: [VaccinationCenterService],
  imports: [FormsModule, CommonModule ], 
  templateUrl: './liste-centres.component.html',
  styleUrl: './liste-centres.component.css'
})
export class ListeCentresComponent implements OnInit {

  centers: VaccinationCenter[] = [];  // Centres de vaccination récupéré
  center?: VaccinationCenter;  // Centre sélectionné
  isCenterSelected: boolean = false;  // Indicateur de sélection
  isListCentersVisible = true; // Variable pour contrôler l'affichage de la liste des centres
  
  constructor(private vaccinationCenterService: VaccinationCenterService,private location: Location, private emitterService: EmitterService) {}

  ngOnInit(): void {
    // Récupérer les centres via le service
    this.vaccinationCenterService.loadCenters();
    this.vaccinationCenterService.centers$.subscribe((centers) => {
    this.centers = centers;
    // console.log('Centres de vaccination récupérés :', this.centers);

    
  
    });}

     // Fonction de sélection d'un centre
  selectCenter(center: VaccinationCenter) {
    this.center = center;
    this.isCenterSelected = true;
    console.log('selectCenter');
    //this.centerSelected.emit(true);
  }

      //fonction pour annuler la sélection
  cancelDisplay() { 
    this.center = undefined;
    this.isCenterSelected = false;
    console.log('Revenir en arrière');
    this.emitterService.emitEvent();
    this.location.back();

  }


}



