import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SearchModule } from '../search-center/search.module';
import { PriseRdvVerifMailComponent } from './prise-rdv-verif-mail.component';
import { PriseRdvModule } from "../prise-rdv/prise-rdv.module"; // Composant de prise de rendez-vous

@NgModule({
  imports: [CommonModule, FormsModule, SearchModule, PriseRdvModule],  // Importation des modules nécessaires
  declarations: [PriseRdvVerifMailComponent],
  exports: [PriseRdvVerifMailComponent]  // Exportation pour utilisation dans d'autres modules
})
export class PriseRdvModuleVerifMail { }
