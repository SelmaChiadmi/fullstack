import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-super-admins',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './super-admins.component.html',
  styleUrl: './super-admins.component.css'
})
export class SuperAdminsComponent {
  isConfirmationDisplayed: boolean = false;

   // Fonction pour afficher le message de confirmation
   deleteSuperadmin() {
    this.isConfirmationDisplayed = true;
}

// Fonction pour fermer le message de confirmation
closeConfirmation() {
    this.isConfirmationDisplayed = false;
}

}
