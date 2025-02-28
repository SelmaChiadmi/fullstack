import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mycenter',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './mycenter.component.html',
  styleUrl: './mycenter.component.css'
})
export class MycenterComponent {

  isConfirmationDisplayed: boolean = false;
     // Fonction pour afficher le message de confirmation
  delete() {
      this.isConfirmationDisplayed = true;
  }
  
  // Fonction pour fermer le message de confirmation
  closeConfirmation() {
      this.isConfirmationDisplayed = false;
  }
}
