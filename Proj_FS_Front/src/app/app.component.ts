import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router'; // Import du Router pour détecter l'URL
import { RouterOutlet } from '@angular/router';
import { SearchCenterComponent } from "./features/reservations/search-center/search-center.component";
import { ButtonToLoginComponent } from "./features/admins/button-to-login/button-to-login.component";
import { AdminMenuComponent } from './features/admins/admin-menu/admin-menu.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SearchCenterComponent, ButtonToLoginComponent, FormsModule, CommonModule, AdminMenuComponent],
  providers: [],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  ngOnInit(): void {
    
  }
  // Titre de l'application
  title = 'front_end_patients';

  // Variable pour afficher ou cacher <app-search-center>
  showSearchCenter: boolean = true;  

  // Variable qui indique si un centre a été sélectionné
  isCenterSelected: boolean = false;  

  // Variable pour contrôler l'affichage global du composant
  isthisComponentVisible = true;  

  // Indique si l'on est sur la page d'accueil ('/')
  isHomePage: boolean = false;  

  /**
   * Constructeur : écoute les changements d'URL pour savoir si l'on est sur la page d'accueil
   */
  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Vérifie si l'URL actuelle est la page d'accueil ('/')
        this.isHomePage = this.router.url === '/public';
      }
    });
  }

  /**
   * Méthode pour afficher ou masquer <app-search-center>
   * @param dontshow - Booléen reçu depuis un événement enfant
   */
  toggleSearchCenter(dontshow: boolean) {
    this.showSearchCenter = dontshow;
  }

  /**
   *  Méthode appelée après la déconnexion pour réafficher la barre de recherche
   */
  displaySearchBar(show: boolean) {
    console.log('displaySearchBar', show);
    this.showSearchCenter = show;
  }

  /**
   *  Méthode déclenchée lorsqu'un centre est sélectionné
   */
  onCenterSelected(isSelected: boolean) {
    this.isCenterSelected = isSelected;
     // Indiquer qu'un centre a été sélectionné

  }

  
  /**
   *  Méthode pour masquer le composant actuel
   */
  onHideSearchCenter(): void {
    this.isthisComponentVisible = false;
  }


}
