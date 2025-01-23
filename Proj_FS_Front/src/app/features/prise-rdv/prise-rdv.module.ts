import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SearchModule } from '../search-center/search.module';
import { PriseRdvComponent } from './prise-rdv.component'; // Composant de prise de rendez-vous

@NgModule({
  declarations: [PriseRdvComponent],  // Déclaration du composant dans le module
  imports: [CommonModule, FormsModule,SearchModule],  
  exports: [PriseRdvComponent]  // Exportation pour utilisation dans d'autres modules
})
export class PriseRdvModule { }
