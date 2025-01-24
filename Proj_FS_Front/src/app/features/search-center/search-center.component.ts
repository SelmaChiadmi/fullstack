import { Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VaccinationCenter } from '../../core/models/vaccination-centers.model';
import { VaccinationCenterService } from '../../core/services/vaccination-centers.service';
import { PriseRdvComponent } from '../prise-rdv/prise-rdv.component';
import { PriseRdvVerifMailComponent } from '../prise-rdv-verif-mail/prise-rdv-verif-mail.component';


@Component({
  selector: 'app-search-center',
  standalone: true,
  providers: [VaccinationCenterService],
  imports: [FormsModule, CommonModule , PriseRdvComponent,PriseRdvVerifMailComponent], 
  templateUrl: './search-center.component.html',
  styleUrl: './search-center.component.css'
})
export class SearchCenterComponent implements OnInit {
  centers: VaccinationCenter[] = [];  // Centres de vaccination récupérés
  filteredCenters: VaccinationCenter[] = [];  // Centres filtrés
  searchText: string = '';  // Texte de recherche
  center?: VaccinationCenter;  // Centre sélectionné
  isCenterSelected: boolean = false;  // Indicateur de sélection
  isSearch: boolean = false;  // Indicateur de recherche


  constructor(private vaccinationCenterService: VaccinationCenterService) {}

  ngOnInit(): void {
    // Récupérer les centres via le service
    this.vaccinationCenterService.loadCenters();
    this.vaccinationCenterService.centers$.subscribe((centers) => {
      this.centers = centers;
      this.filteredCenters = []; 
    });

    //Debug
    //console.log('ngOnInit');
    //console.log(this.centers);
  }

  // Fonction de recherche
  onSearchEnter() {
    const searchText = this.searchText.trim().toLowerCase();
    
    if (searchText === '') {
      this.filteredCenters = [];
    } else {
      this.filteredCenters = this.centers.filter(center =>
        center.ville.toLowerCase().includes(searchText) 
      );
    }
    this.isSearch = true;

  

    // Debug
    // console.log('onSearchEnter');
    // console.log(this.filteredCenters);
    // console.log(this.centers);
    // console.log(this.searchText);

  }

  // Fonction de sélection d'un centre
  selectCenter(center: VaccinationCenter) {
    this.center = center;
    this.filteredCenters = []; //enlever les centres filtrés pour afficher les détails à la place
    this.isCenterSelected = true;
    console.log('selectCenter');
  }

  //fonction pour annuler la sélection
  cancelSelection() { 
    this.center = undefined;
    this.isCenterSelected = false;
    console.log('cancelSelection');
    this.isSearch = false;

  }

  // Fonction de suppression d'un centre
  delete(center: VaccinationCenter) {
    this.center = undefined;
    this.filteredCenters = this.filteredCenters.filter(c => c.id !== center.id);
  }
}
