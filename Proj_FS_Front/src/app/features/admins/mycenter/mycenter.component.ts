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
  // add-employee.component.ts

  isOpen = false;
  formData = {
    nom: '',
    prenom: '',
    email: '',
    password: '',
    centre: ''
  };

  openForm() {
    this.isOpen = true;
  }

  closeForm() {
    this.isOpen = false;
    this.resetForm();
  }

  onSubmit() {
    console.log('Nouvel employé :', this.formData);
    this.closeForm(); // Ferme le formulaire après soumission
  }

  private resetForm() {
    this.formData = {
      nom: '',
      prenom: '',
      email: '',
      password: '',
      centre: ''
    };
  }

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
