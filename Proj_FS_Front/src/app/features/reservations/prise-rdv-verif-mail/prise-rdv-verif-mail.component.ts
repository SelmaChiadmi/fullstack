import { Component, Input,Output,EventEmitter } from '@angular/core';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PriseRdvComponent } from '../prise-rdv/prise-rdv.component';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { EmailVerificationService } from '../../../core/services/email_verif.service';
import { CreneauService } from '../../../core/services/creneau.service';
import { Patient } from '../../../core/models/patients.model';
import { ReservationService } from '../../../core/services/reservations.service';


@Component({
  selector: 'app-prise-rdv-verif-mail',
  standalone: true,
  imports: [FormsModule, CommonModule,PriseRdvComponent],
  providers: [
    VaccinationCenterService,
    EmailVerificationService,
    CreneauService
  ],
  templateUrl: './prise-rdv-verif-mail.component.html',
  styleUrl: './prise-rdv-verif-mail.component.css'
})
export class PriseRdvVerifMailComponent {

  constructor(private emailVerificationService: EmailVerificationService , private creneauService: CreneauService,private reservationService : ReservationService) {}



  @Input() center!: VaccinationCenter; // Centre sélectionné
  @Input() creneaux: string[] = []; // Horaires disponibles spécifiques au centre
  @Input() receivedCenterId?: number; // ID du centre sélectionné 
  @Output() onCancel = new EventEmitter<void>(); // Annulation
  @Output() centerIdEmitter = new EventEmitter<number>(); // Émet l'ID du centre sélectionné


  // Variables pour le formulaire de mail connu
  mail: string = '';
  chosenDate: string = '';
  chosenTime: string = ''; 
  errorMessage: string = '';

// Booléen pour afficher le formulaire d'entrée du mail
  isMailGiven: boolean = false;

// Booléen pour valider la présence du mail dans la base de données
  MailisinDatabase: boolean = false;

  // Booléen pour afficher la confirmation
  confirmation: boolean = false;

  //mail prédéfini pour le formulaire de mail inconnu : pré-remplissage du mail et ne pas donner la possibilité de le modifier
  predefinedMail: string = '';

  // Méthode qui pourrait être appelée lorsqu'on veut transmettre l'ID
  sendCenterId() {
    this.centerIdEmitter.emit(this.receivedCenterId);
  }

  
  // annuler la prise de rendez-vous
  cancel() {
    this.onCancel.emit();
    this.resetForm();
  }

  // Fonction pour valider le formulaire 
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

  // Fonction pour valider le rendez-vous
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
     // Vérifier si le créneau a été choisi
    if (!this.chosenTime) {
      this.errorMessage = 'Le créneau horaire est manquant.';
      return false;
    }


    this.errorMessage = '';
    console.log('validateAppointment');
    return true
  }

  // Fonction pour soumettre l'email
  submitMail() {
    if (this.validateForm()) {
      this.emailVerificationService.verifyEmail(this.mail).subscribe({
        next: (exists: boolean) => {
          if (exists) {
            console.log('L\'email existe dans la base de données.');
            this.MailisinDatabase = true;
          } else {
            console.log('L\'email n\'existe pas.');
            this.MailisinDatabase = false;
            this.predefinedMail = this.mail;
          }
          this.isMailGiven = true; // Pour indiquer que l'utilisateur a bien soumis un email
        },
        error: () => {
          // En cas d'erreur, on suppose que l'email n'existe pas
          console.error('Erreur lors de la vérification de l\'email. mail inexistant dans la base de données.');
          this.MailisinDatabase = false; // Considérer comme non trouvé
          this.isMailGiven = true; // Email a été soumis, même si erreur
        },
      });
    } else {
      console.error(this.errorMessage);
      this.MailisinDatabase = false;
      this.isMailGiven = false;
    }
  }

  // Fonction pour récupérer les créneaux disponibles
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
  

   // Fonction pour enlever les 3 derniers caractères (les secondes) aux créneaux
   formatCreneau(creneau: string): string {
    return creneau.slice(0, -3); // Enlever les 3 derniers caractères
  }
  
  
 // Fonction pour soumettre le rendez-vous (pas encore lié à un service)
 submitAppointment() {
  
  console.log('submitAppointment');
  if (this.validateAppointment()) {    
    console.log('Mail:', this.mail);
    console.log('Date choisie:', this.chosenDate);
    console.log('Heure choisie:', this.chosenTime);
    this.confirmation = true;
    //this.makeReservation(); //-->il faut récupérer les données du patient pour les envoyer à la méthode makeReservation
  } else {
    console.error(this.errorMessage);
  }
}


    // Fonction pour effectuer la réservation
    makeReservation(patient: Patient) {
      const birthDate = new Date(patient.birthDate);
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
      this.reservationService.bookAppointment(this.center.id, this.chosenDate, this.chosenTime, appointmentData.patientDto).subscribe(
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



   // Réinitialiser le formulaire pour prendre un autre rendez-vous
   resetForm() {   
    this.mail = '';
    this.chosenDate = '';
    this.chosenTime = '';
    this.MailisinDatabase = false; // Cache la confirmation et réinitialise le formulaire
    this.confirmation = false; // Cache la confirmation et réinitialise le formulaire
  }

   
}
