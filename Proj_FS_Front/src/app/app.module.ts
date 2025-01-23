// app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { SearchModule } from './features/search-center/search.module';
import { PriseRdvModule } from './features/prise-rdv/prise-rdv.module';

@NgModule({
  imports: [
    BrowserModule,
    AppComponent, // Importation du module de recherche
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
