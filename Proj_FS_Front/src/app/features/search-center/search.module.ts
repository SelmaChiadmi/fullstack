
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchCenterComponent } from './search-center.component'; // Composant de recherche
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [CommonModule, FormsModule, SearchCenterComponent],
  exports: [SearchCenterComponent] // On l'exporte pour pouvoir l'utiliser ailleurs
})
export class SearchModule { }
