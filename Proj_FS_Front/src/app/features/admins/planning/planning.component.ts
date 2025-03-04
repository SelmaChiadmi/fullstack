import { Component, OnInit } from '@angular/core';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { PlanningService } from '../../../core/services/planning.service';  // Assurez-vous d'importer le service Planning
import { CommonModule } from '@angular/common';
import { ReservationService } from '../../../core/services/reservations.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-planning',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './planning.component.html',
  styleUrls: ['./planning.component.css']
})
export class PlanningComponent implements OnInit {
  centre: any = null;
  errorMessage: string = '';
  selectedDate: string = '';  // Date sélectionnée au format YYYY-MM-DD
  reservations: any[] = [];  // Tableau pour stocker les réservations

  constructor(
    private centreService: VaccinationCenterService,
    private planningService: PlanningService,
    private reservationService: ReservationService,
  ) {}

  ngOnInit(): void {
    this.loadCentre();
    // Initialisation avec la date d'aujourd'hui
    const today = new Date();
    this.selectedDate = today.toISOString().split('T')[0];  // Format YYYY-MM-DD
    this.loadReservations();
  }

  loadCentre(): void {
    this.centreService.getCentreByAdmin().subscribe({
      next: (data) => {
        this.centre = data;
      },
      error: (error) => {
        if (error.status === 403) {
          alert("Vous n'êtes pas admin ou médecin ");
        } else {
          this.errorMessage = 'Erreur lors du chargement du centre.';
          console.error(error);
        }
      }
    });
  }

  // Fonction pour aller au jour précédent
  previousDay(): void {
    const previousDate = new Date(this.selectedDate);
    previousDate.setDate(previousDate.getDate() - 1);
    this.selectedDate = previousDate.toISOString().split('T')[0];
    this.loadReservations();
  }

  // Fonction pour aller au jour suivant
  nextDay(): void {
    const nextDate = new Date(this.selectedDate);
    nextDate.setDate(nextDate.getDate() + 1);
    this.selectedDate = nextDate.toISOString().split('T')[0];
    this.loadReservations();
  }

  // Fonction pour charger les réservations
  loadReservations(): void {
    this.planningService.getResaByDate(this.selectedDate).subscribe({
      next: (data) => {
        this.reservations = data;  
        console.log('Réservations:', this.reservations);
      },
      error: (error) => {
        this.errorMessage = 'Erreur lors de la récupération des réservations.';
        console.error(error);
      }
    });
  }

  // Fonction pour changer la date et recharger les réservations
  changeDate(newDate: string): void {
    this.selectedDate = newDate;
    this.loadReservations();
  }

  onDateChange(): void {
    this.loadReservations();
  }
 

  toggleStatut(reservation: any): void {
    
    const newStatus = !reservation.statutReservation;
    this.reservationService.updateReservationValidation(reservation.id, newStatus)
      .subscribe(
        response => {
          // Si la réponse est réussie, met à jour le statut de la réservation localement
            reservation.statutReservation = newStatus;
            alert('Le statut de la réservation a été mis à jour avec succès.');
            console.log('Statut de la réservation mis à jour avec succès:', 200);
        
        },
        error => {
          if (error.status === 403) {
            alert("Vous n'êtes pas autorisé à changer le statut de cette réservation.");
            console.log(error.message)
          }else{
          // En cas d'erreur, afficher un message d'erreur
          console.error('Erreur lors de la mise à jour du statut :', error.message);
          alert('Une erreur est survenue lors de la mise à jour du statut.');
        }}
      );
      

      
  }


}
