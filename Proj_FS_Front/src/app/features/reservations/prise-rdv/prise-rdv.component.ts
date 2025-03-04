import { Component, Input, Output, EventEmitter } from '@angular/core';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CreneauService } from '../../../core/services/creneau.service';
import { PatientService } from '../../../core/services/patients.service';
import { Patient } from '../../../core/models/patients.model';
import { ReservationService } from '../../../core/services/reservations.service';
import { HttpErrorResponse } from '@angular/common/http';
import { JourDispoService } from '../../../core/services/joursdispo.service';

@Component({
  selector: 'app-prise-rdv',
  standalone: true,
  imports: [FormsModule, CommonModule],
  providers: [CreneauService, PatientService, ReservationService],
  templateUrl: './prise-rdv.component.html',
  styleUrls: ['./prise-rdv.component.css'],
})
export class PriseRdvComponent {
  @Input() center?: VaccinationCenter;
  @Input() receivedCenterId!: number;
  @Input() creneaux: string[] = [];
  @Output() onCancel = new EventEmitter<void>();
  @Input() mail: string = '';

  constructor(private creneauService: CreneauService, private patientService: PatientService, private reservationService: ReservationService, private jourDispoService: JourDispoService) {}

  name: string = '';
  surname: string = '';
  birthDate: string = '';
  phone: string = '';
  chosenDate: string = '';
  chosenTime: string = '';
  errorMessage: string = '';
  confirmation: boolean = false;
  hasRedBorder: boolean = false;
  availableDates: string[] = []; // Liste des dates disponibles

  // Vérifie si un ID de centre a été reçu lors de changements d'entrée
  ngOnChanges() {
    if (this.receivedCenterId !== undefined) {
      console.log('ID reçu dans PriseRdvComponent :', this.receivedCenterId);
    } else {
      console.log('Aucun ID de centre reçu.');
    }
  }

  // Annule la prise de rendez-vous
  cancel() {
    this.onCancel.emit();
  }

  // Valide les champs du formulaire avant soumission
  validateForm(): boolean {
    if (!this.name || !this.surname || !this.mail || !this.birthDate || !this.phone || !this.chosenDate || !this.chosenTime) {
      this.errorMessage = 'Des champs sont manquants.';
      this.hasRedBorder = true;
      return false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.mail)) {
      this.errorMessage = "L'adresse email est invalide.";
      this.hasRedBorder = true;
      return false;
    }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(this.phone)) {
      this.errorMessage = 'Le numéro de téléphone est invalide.';
      this.hasRedBorder = true;
      return false;
    }

    const birthDate = new Date(this.birthDate);
    const today = new Date();
    if (birthDate >= today) {
      this.errorMessage = 'La date de naissance est invalide.';
      this.hasRedBorder = true;
      return false;
    }

    const chosenDateObj = new Date(this.chosenDate);
    if (chosenDateObj <= today) {
      this.errorMessage = 'La date choisie doit être dans le futur.';
      this.hasRedBorder = true;
      return false;
    }

    this.errorMessage = '';
    return true;
  }

  // Soumet la demande de rendez-vous après validation
  submitAppointment() {
    if (this.validateForm()) {
      const patientData: Patient = {
        lastName: this.name,
        firstName: this.surname,
        email: this.mail,
        telephone: Number(this.phone),
        birthDate: new Date(this.birthDate),
      };

      this.makeReservation(patientData);
    } else {
      this.hasRedBorder = true;
      console.error(this.errorMessage);
      this.triggerAnimation();
    }
  }

  // Effectue une réservation via le service
  makeReservation(patient: Patient) {
    this.reservationService.bookAppointment(this.receivedCenterId, this.chosenDate, this.chosenTime, patient).subscribe(
      (response) => {
        console.log('Réservation effectuée avec succès:', response);
        this.confirmation = true;
      },
      (error: HttpErrorResponse) => {
        console.error('Erreur lors de la réservation:', error);
        // le status 201 correspond à une réservation effectuée avec succès et non une erreur
        if (error.status === 201) { 
          console.log('Réservation effectuée avec succès:', error);
          this.confirmation = true;
        } else {
          alert(error.error?.message || 'Une erreur est survenue lors de la réservation.');
        }
      }
    );
  }

  // Déclenche une animation en cas d'erreur
  triggerAnimation() {
    const formElement = document.querySelector('.affichage_info_centre');
    if (formElement) {
      formElement.classList.remove('change-border-color');
      setTimeout(() => {
        formElement.classList.add('change-border-color');
      }, 50);
    }
  }


  // Méthode pour récupérer les jours disponibles
  getAvailableDates(): void {
    console.log('ID reçu dans PriseRdvComponent :', this.receivedCenterId);

    if (this.receivedCenterId) {
      this.jourDispoService.getJoursDisponibles(this.receivedCenterId).subscribe(
        (data: string[]) => {
          console.log('Dates disponibles:', this.availableDates);
          this.availableDates = data; 
          
        },
        (error) => {
          console.error('Erreur lors de la récupération des dates disponibles', error);
        }
      );
    }
    else{
      console.error('ID de centre non défini.');
    }
  }

   // Méthode pour gérer le changement de la date sélectionnée
   onDateChange(): void {
    if (this.chosenDate && this.center) {
      this.creneauService.getCreneaux(this.center.id, this.chosenDate).subscribe(
        (data) => {
          this.creneaux = data;
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
  // Formate l'affichage des créneaux horaires
  formatCreneau(creneau: string): string {
    return creneau.slice(0, -3);
  }

  // Réinitialise le formulaire après soumission
  resetForm() {
    this.name = '';
    this.surname = '';
    this.mail = '';
    this.birthDate = '';
    this.phone = '';
    this.chosenDate = '';
    this.chosenTime = '';
    this.confirmation = false;
  }
}
