// app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { routes } from './app.routes';
import { ListeCentresComponent } from './features/reservations/liste-centres/liste-centres.component';
import { RouterModule } from '@angular/router';
import { ButtonShowListCentersComponent } from './features/reservations/button-show-list-centers/button-show-list-centers.component';

@NgModule({
  imports: [
    BrowserModule,
    AppComponent, // Importation du module de recherche
    ListeCentresComponent, // Importation du module de liste des centres
    RouterModule.forRoot(routes), // Importation des routes
    ButtonShowListCentersComponent // Importation du module de bouton pour afficher la liste des centres
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
