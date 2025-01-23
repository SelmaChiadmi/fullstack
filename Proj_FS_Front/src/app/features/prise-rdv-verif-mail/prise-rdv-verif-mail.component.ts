import { Component, Input,Output,EventEmitter } from '@angular/core';
import { VaccinationCenter } from '../../core/models/vaccination-centers.model';

@Component({
  selector: 'app-prise-rdv-verif-mail',
  templateUrl: './prise-rdv-verif-mail.component.html',
  styleUrl: './prise-rdv-verif-mail.component.css'
})
export class PriseRdvVerifMailComponent {

  @Input() center?: VaccinationCenter; // Centre sélectionné
  @Input() creneaux: string[] = []; // Horaires disponibles spécifiques au centre
  @Output() onCancel = new EventEmitter<void>(); // Annulation

  // Variables pour le formulaire de mail connu
  mail: string = '';
  chosenDate: string = '';
  chosenTime: string = ''; 
  errorMessage: string = '';

// Booléen pour valider la présence du mail dans la base de données
  MailisinDatabase: boolean = false;

  // Booléen pour afficher la confirmation
  confirmation: boolean = false;
  

  cancel() {
    this.onCancel.emit();
    this.resetForm();
  }


  validateForm(): boolean {
    if (!this.mail) {
      this.errorMessage = 'Mail manquant';
      return false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.mail)) {
      this.errorMessage = "L'adresse email est invalide.";
      return false;
    }

    const today = new Date();
    const chosenDateObj = new Date(this.chosenDate);

 

    this.errorMessage = '';
    return true;
  }

  validateAppointment(): boolean {
    const today = new Date();
    const chosenDateObj = new Date(this.chosenDate);

    if (!this.chosenDate) {
      this.errorMessage = 'Date manquante';
      return false;
    }
    
    if (chosenDateObj <= today) {
      this.errorMessage = 'La date de rendez-vous doit être ultérieure à aujourd\'hui.';
      return false;
    }

    if (!this.chosenTime) {
      this.errorMessage = 'Heure manquante';
      return false;
    }

    this.errorMessage = '';
    console.log('validateAppointment');
    return true
  }

  submitMail() {
    if (this.validateForm()) {    
      console.log('Mail:', this.mail);
      // confirmation 
      this.MailisinDatabase = true;
      console.log('MailisinDatabase:', this.MailisinDatabase);
    } else {
      console.error(this.errorMessage);
      
    }
  }

 submitAppointment() {
  console.log('submitAppointment');
  if (this.validateAppointment()) {    
    console.log('Mail:', this.mail);
    console.log('Date choisie:', this.chosenDate);
    console.log('Heure choisie:', this.chosenTime);
    this.confirmation = true;


  } else {
    console.error(this.errorMessage);
  }}


   // Réinitialiser le formulaire pour prendre un autre rendez-vous
   resetForm() {   
    this.mail = '';
    this.chosenDate = '';
    this.chosenTime = '';
    this.MailisinDatabase = false; // Cache la confirmation et réinitialise le formulaire
    this.confirmation = false; // Cache la confirmation et réinitialise le formulaire

    
  }
}
