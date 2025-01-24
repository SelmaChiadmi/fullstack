import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SearchCenterComponent } from "./features/reservations/search-center/search-center.component";
import { ButtonToLoginComponent } from "./features/admins/button-to-login/button-to-login.component";
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SearchCenterComponent, ButtonToLoginComponent,FormsModule,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Faites vous vacciner !';
  showSearchCenter: boolean = true; // Contrôle l'affichage de <app-search-center>

  // Méthode appelée depuis le composant enfant
  toggleSearchCenter(show: boolean) {
    this.showSearchCenter = show;
  }
  

}
