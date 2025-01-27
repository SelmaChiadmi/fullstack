import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SearchCenterComponent } from "./features/reservations/search-center/search-center.component";
import { ButtonToLoginComponent } from "./features/admins/button-to-login/button-to-login.component";
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ButtonShowListCentersComponent } from './features/reservations/button-show-list-centers/button-show-list-centers.component';
import { ListeCentresComponent } from './features/reservations/liste-centres/liste-centres.component';
import { EmitterService } from './core/services/emitter.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SearchCenterComponent, ButtonToLoginComponent,FormsModule,CommonModule,ButtonShowListCentersComponent,ListeCentresComponent],
  providers: [EmitterService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  

  constructor( private emitterService: EmitterService , private location: Location) {}


  ngOnInit(): void {   
    

    //pour cacher la liste des centres
    this.emitterService.event$.subscribe(() => {
      this.isListCentersVisible = true;
      this.isthisComponentVisible = true
      //console.log('isListCentersVisible:', this.isListCentersVisible);
      
    });
    this.location.back();

    

    
    
  }

  title = 'Faites vous vacciner !';
  showSearchCenterAndListCenterButton: boolean = true; // Contrôle l'affichage de <app-search-center> : composant de recherche d'un centre 

  // Méthode appelée depuis le composant enfant
  toggleSearchCenter(show: boolean) {
    this.showSearchCenterAndListCenterButton = show;
  }

  
  isCenterSelected: boolean = false;  // Indicateur de sélection du centre
  // si un centre est selectionné, ne pas afficher le titre et les blocs d'infos supplémentaires (:savoir si il faut se faire vacciner et remonter le moral)
  onCenterSelected(isSelected: boolean) {
    this.isCenterSelected = isSelected;  // Mettre à jour l'état du centre sélectionné
  }

  isListCentersVisible = true; // Variable pour contrôler l'affichage de la liste des centres
  isthisComponentVisible = true // Variable pour contrôler l'affichage du composant associé


  onHideSearchCenter(): void {
    this.isListCentersVisible = true; // Inverse l'état de visibilité de la liste des centres
    this.isthisComponentVisible = false
    console.log('hideSearchCenter called, isListCentersVisible:', this.isListCentersVisible);  // Vérification dans la console
  }



  

}
