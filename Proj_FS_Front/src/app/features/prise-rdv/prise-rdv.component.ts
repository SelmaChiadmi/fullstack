import { Component, Input, Output, EventEmitter } from '@angular/core';
import { VaccinationCenter } from '../../core/models/vaccination-centers.model';

@Component({
  selector: 'app-prise-rdv',
  templateUrl: './prise-rdv.component.html',
  styleUrls: ['./prise-rdv.component.css'],
})
export class PriseRdvComponent {
  @Input() center?: VaccinationCenter; // Centre sélectionné
  @Input() creneaux: string[] = []; // Horaires disponibles spécifiques au centre
  @Output() onCancel = new EventEmitter<void>(); // Annulation

  // Variables pour le formulaire
  name: string = '';
  surname: string = '';
  mail: string = '';
  birthDate: string = '';
  phone: string = '';
  chosenDate: string = '';
  chosenTime: string = ''; 
  errorMessage: string = '';

  // Booléen pour afficher la confirmation
  confirmation: boolean = false;

  cancel() {
    this.onCancel.emit();
  }


  validateForm(): boolean {
    if (!this.name || !this.surname || !this.mail || !this.birthDate || !this.phone || !this.chosenDate || !this.chosenTime) {
      this.errorMessage = 'Des champs sont manquants.';
      return false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.mail)) {
      this.errorMessage = "L'adresse email est invalide.";
      return false;
    }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(this.phone)) {
      this.errorMessage = 'Le numéro de téléphone est invalide.';
      return false;
    }

    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
    if (!dateRegex.test(this.birthDate)) {
      this.errorMessage = 'La date de naissance est invalide.';
      return false;
    }

    const birthDate = new Date(this.birthDate);
    const today = new Date();
    const age = today.getFullYear() - birthDate.getFullYear();
    const isBirthdayPassed =
      today.getMonth() > birthDate.getMonth() ||
      (today.getMonth() === birthDate.getMonth() && today.getDate() >= birthDate.getDate());

    if (age < 18 || (age === 18 && !isBirthdayPassed)) {
      this.errorMessage = 'Vous devez avoir au moins 18 ans pour prendre rendez-vous.';
      return false;
    }

     // Vérifier si la date choisie est bien après la date actuelle
     const chosenDateObj = new Date(this.chosenDate);
     
     if (chosenDateObj <= today) {
       this.errorMessage = 'La date choisie doit être dans le futur.';
       return false;
     }

    this.errorMessage = '';
    return true;
  }

  submitAppointment() {
    if (this.validateForm()) {
      console.log('Nom:', this.name);
      console.log('Prénom:', this.surname);
      console.log('Centre:', this.center);
      console.log('Mail:', this.mail);
      console.log('Téléphone:', this.phone);
      console.log('Date de naissance:', this.birthDate);
      console.log('Date choisie:', this.chosenDate);
      console.log('Heure choisie:', this.chosenTime);
      // confirmation 
      this.confirmation = true;
    
    } else {
      console.error(this.errorMessage);
      
    }
  }

   // Réinitialiser le formulaire pour prendre un autre rendez-vous
   resetForm() {
    this.name = '';
    this.surname = '';
    this.mail = '';
    this.birthDate = '';
    this.phone = '';
    this.chosenDate = '';
    this.chosenTime = '';
    this.confirmation = false; // Cache la confirmation et réinitialise le formulaire
  }
}
