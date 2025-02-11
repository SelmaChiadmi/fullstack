import { Component, Input, Output, EventEmitter } from '@angular/core';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CreneauService } from '../../../core/services/creneau.service';
import { PatientService } from '../../../core/services/patients.service';
import { Patient } from '../../../core/models/patients.model';
import { ReservationService } from '../../../core/services/reservations.service';

@Component({
  selector: 'app-prise-rdv',
  standalone: true,
  imports: [FormsModule, CommonModule],
  providers: [CreneauService, PatientService, ReservationService],
  templateUrl: './prise-rdv.component.html',
  styleUrls: ['./prise-rdv.component.css'],
})
export class PriseRdvComponent {
  @Input() center?: VaccinationCenter; // Centre sélectionné
  @Input() receivedCenterId!: number; // ID du centre sélectionné 
  @Input() creneaux: string[] = []; // Horaires disponibles spécifiques au centre
  @Output() onCancel = new EventEmitter<void>(); // Annulation
  @Input() mail: string = ''; // Réception de l'email pré-rempli

  constructor(private creneauService: CreneauService, private patientService: PatientService, private reservationService : ReservationService) {}

  // Variables pour le formulaire
  name: string = '';
  surname: string = '';
  birthDate: string = '';
  phone: string = '';
  chosenDate: string = '';
  chosenTime: string = ''; 
  errorMessage: string = '';
  

  // Booléen pour afficher la confirmation
  confirmation: boolean = false;


  // Cela permet de changer la couleur de la bordure dans le cas ou il n'y a pas de validation dans un des formulaire
  hasRedBorder: boolean = false;  // Variable de contrôle pour savoir si la bordure doit être rouge

  //lorsqu'on reçoit un ID de centre, on l'affiche dans la console
  ngOnChanges() {
    if (this.receivedCenterId !== undefined) {
      console.log('ID reçu dans PriseRdvComponent :', this.receivedCenterId);
    } else {
      console.log('Aucun ID de centre reçu.');
    }
  }
   
  //annuler la prise de rendez-vous
  cancel() {
    this.onCancel.emit();
  }



  //Vérification du formulaire
  validateForm(): boolean {

    if (!this.name || !this.surname || !this.mail || !this.birthDate || !this.phone || !this.chosenDate || !this.chosenTime) {
      this.errorMessage = 'Des champs sont manquants.';
      this.hasRedBorder= true;
      return false;
    }
   
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.mail)) {
      this.errorMessage = "L'adresse email est invalide.";
      this.hasRedBorder= true;
      return false;
    }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(this.phone)) {
      this.errorMessage = 'Le numéro de téléphone est invalide.';
      this.hasRedBorder= true;
      return false;
    }

    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
    if (!dateRegex.test(this.birthDate)) {
      this.errorMessage = 'La date de naissance est invalide.';
      this.hasRedBorder= true;
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
      this.hasRedBorder= true;
      return false;
    }

     // Vérifier si la date choisie est bien après la date actuelle
     const chosenDateObj = new Date(this.chosenDate);
     
     if (chosenDateObj <= today) {
       this.errorMessage = 'La date choisie doit être dans le futur.';
       this.hasRedBorder= true;
       return false;
     }

    this.errorMessage = '';
    return true;
  }

  submitAppointment() {
    if (this.validateForm()) {

    const birthDate = new Date(this.birthDate);
    const patientData: Patient = {
      lastName: this.name,
      firstName: this.surname,
      email: this.mail,
      telephone: Number(this.phone),
      birthDate: birthDate,
    };

   
    
    console.log('Patient data before sending:', patientData); // Fonctionne jusqu'ici : le patient data dispose de tout
    this.patientService.createPatient(patientData).subscribe(
      (response) => {
        console.log('Patient créé avec succès :', response);
        // Après avoir créé le patient, procéder à la réservation
        this.makeReservation(response);  // Appeler la fonction pour réserver le créneau
        console.log('Reservation effectuée');
      },
      (error) => {
        console.error('Erreur lors de la création du patient :', error);
      }
    );
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
      this.hasRedBorder= true;
      console.error(this.errorMessage);
      this.triggerAnimation();
      
    }
  }

  
  // Fonction pour effectuer la réservation
  makeReservation(patient: Patient) {
    const birthDate = new Date(this.birthDate);
    const appointmentData = {
      date: this.chosenDate,
      heure: this.chosenTime,
      patientDto: {
        lastName: patient.lastName,
        firstName: patient.firstName,
        email: patient.email,
        telephone: patient.telephone,
        birthDate: patient.birthDate 
      }
    };
  
    // Appel à ReservationService pour réserver
    this.reservationService.bookAppointment(this.receivedCenterId, this.chosenDate, this.chosenTime, appointmentData.patientDto).subscribe(
      (response) => {
        console.log('Réservation effectuée avec succès:', response);
        this.confirmation = true; // Afficher la confirmation de la réservation
      },
      (error) => {
        console.error('Erreur lors de la réservation:', error);
        alert('Une erreur est survenue lors de la réservation.');
      }
    );
  }



  //Permet une animation de la bordure du formulaire en cas d'erreur sur le remplissage des champs
  triggerAnimation() {
    const formElement = document.querySelector('.affichage_info_centre');  // Sélectionner le formulaire ou un élément spécifique
    if (formElement) {
      // Supprimer la classe d'animation
      formElement.classList.remove('change-border-color');
      
      // Forcer un petit délai avant de la réajouter
      setTimeout(() => {
        formElement.classList.add('change-border-color');
      }, 50);  // Petit délai pour réinitialiser l'animation
    }
  }

  onDateChange(): void {
    if (this.chosenDate && this.center) { // Vérifie que la date et le centre sont définis
      this.creneauService.getCreneaux(this.center.id, this.chosenDate).subscribe(
        (data) => {
          this.creneaux = data; // Stocke les créneaux récupérés
          console.log('Créneaux récupérés :', this.creneaux);
        },
        (error) => {
          console.error('Erreur lors de la récupération des créneaux', error);
        }
      );
    } else {
      console.error('Date ou centre non défini.');
    }
  }
  availableDates: string[] = []; // Dates disponibles pour le centre sélectionné
  
  
   // Fonction pour enlever les 3 derniers caractères (les secondes) au créneau
   formatCreneau(creneau: string): string {
    return creneau.slice(0, -3); // Enlever les 3 derniers caractères
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
