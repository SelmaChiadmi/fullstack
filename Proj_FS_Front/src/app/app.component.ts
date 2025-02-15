import { Component} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SearchCenterComponent } from "./features/reservations/search-center/search-center.component";
import { ButtonToLoginComponent } from "./features/admins/button-to-login/button-to-login.component";
import { AdminMenuComponent } from './features/admins/admin-menu/admin-menu.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SearchCenterComponent, ButtonToLoginComponent,FormsModule,CommonModule, AdminMenuComponent],
  providers: [],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent{

  title = 'Faites vous vacciner !';
  showSearchCenter: boolean = true; // Contrôle l'affichage de <app-search-center> : composant de recherche d'un centre 

  // Méthode appelée depuis le composant enfant
  toggleSearchCenter(dontshow: boolean) {
    this.showSearchCenter = dontshow;
  }

  displaySearchBar(show: boolean) {
    console.log('displaySearchBar');
    console.log(show);
    this.showSearchCenter = show;
  }


  
  isCenterSelected: boolean = false;  // Indicateur de sélection du centre
  // si un centre est selectionné, ne pas afficher le titre et les blocs d'infos supplémentaires (:savoir si il faut se faire vacciner et remonter le moral)
  onCenterSelected(isSelected: boolean) {
    this.isCenterSelected = isSelected;  // Mettre à jour l'état du centre sélectionné
  }

  isthisComponentVisible = true // Variable pour contrôler l'affichage du composant associé

  onHideSearchCenter(): void {
    this.isthisComponentVisible = false
  }



  

}
