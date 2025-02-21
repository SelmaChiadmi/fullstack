import { Component, OnInit,EventEmitter,Output} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { PriseRdvComponent } from '../prise-rdv/prise-rdv.component';
import { ConfirmRdvComponent } from '../confirm-rdv/confirm-rdv.component'
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-search-center',
  standalone: true,
  providers: [VaccinationCenterService],
  imports: [FormsModule, CommonModule , PriseRdvComponent,ConfirmRdvComponent,RouterModule], 
  templateUrl: './search-center.component.html',
  styleUrl: './search-center.component.css'
})
export class SearchCenterComponent implements OnInit {
  @Output() centerSelected = new EventEmitter<boolean>(); //emet lorsque un centre est selectionné pour enlever l'affichage des composants genants
  @Output() centerIdEmitter = new EventEmitter<number>(); // Émet l'ID du centre sélectionné

  centers: VaccinationCenter[] = [];  // Centres de vaccination récupérés
  filteredCenters: VaccinationCenter[] = [];  // Centres filtrés
  searchText: string = '';  // Texte de recherche
  center?: VaccinationCenter;  // Centre sélectionné
  isCenterSelected: boolean = false;  // Indicateur de sélection
  isSearch: boolean = false;  // Indicateur de recherche


  constructor(private vaccinationCenterService: VaccinationCenterService, private router:Router) {}
  
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

  onSearch(){
    const searchText = this.searchText.trim().toLowerCase();
    
    if (searchText === '') {
      this.filteredCenters = [];
    } else {
      this.filteredCenters = this.centers.filter(center =>
        center.ville.toLowerCase().includes(searchText) 
      );
    }
  }


  
  

  // Fonction de sélection d'un centre
  selectCenter(center: VaccinationCenter) {
    this.center = center;
    this.center.id = center.id;
    console.log('id du centre selectionné:' + this.center.id);
    this.centerIdEmitter.emit(center.id); // Émet l'ID du centre sélectionné
    this.filteredCenters = []; //enlever les centres filtrés pour afficher les détails à la place
    this.isCenterSelected = true;
    console.log('selectCenter');
    this.centerSelected.emit(true);
   
  }

  //fonction pour annuler la sélection
  cancelSelection() { 
    this.center = undefined;
    this.filteredCenters = [];
    this.isCenterSelected = false;
    console.log('cancelSelection');
    this.isSearch = false;
    this.searchText= '';
    this.centerSelected.emit(false); 
    
  }

    @Output() notifyAppComponent = new EventEmitter<boolean>();
    onShowOtherElementsEvent(show: boolean) {
      this.notifyAppComponent.emit(show);  // Passer l'information à app.component
    }

  // Fonction de suppression d'un centre
  delete(center: VaccinationCenter) {
    this.center = undefined;
    this.filteredCenters = this.filteredCenters.filter(c => c.id !== center.id);
  }


}
