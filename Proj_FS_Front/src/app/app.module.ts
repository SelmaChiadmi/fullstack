// app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { routes } from './app.routes';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    BrowserModule,
    AppComponent, // Importation du module de recherche
    RouterModule.forRoot(routes), // Importation des routes
    FormsModule
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
