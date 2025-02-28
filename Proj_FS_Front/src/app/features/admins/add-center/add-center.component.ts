import { Component, EventEmitter, Output } from '@angular/core';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter, VaccinationCenterDto } from '../../../core/models/vaccination-centers.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-add-center',
  templateUrl: './add-center.component.html',
  styleUrls: ['./add-center.component.css'],
  imports: [CommonModule, FormsModule]
})
export class AddCenterComponent {

  center: VaccinationCenterDto = { nom: '', ville: '' };  // Objet centre vide
  successMessage: string = '';  // Message de succès
  confirmation: boolean = false;  // Indicateur de confirmation
  @Output() centerAdded: EventEmitter<void> = new EventEmitter();  

  constructor(private vaccinationCenterService: VaccinationCenterService) {}

  // Méthode pour appeler le service et ajouter un centre
  addCentre() {
    if (!this.center.nom || !this.center.ville) {
      alert('Le nom et la ville ne doivent pas être vides');
      return;
    }
    this.vaccinationCenterService.addCenter(this.center)
      .subscribe(
        (response) => {
          console.log('Réponse du backend:', response);
          this.successMessage = 'Centre ajouté avec succès';
          this.confirmation = true; // Active l'indicateur de confirmation
         
        },
        (error) => {
          if (error.status === 201) {
            console.log('Réponse du backend:', error);
            this.successMessage = 'Centre ajouté avec succès';
          }
          console.error('Erreur lors de l\'ajout:', error.message);
          alert(`Erreur lors de l'ajout : ${error.message}`);
        }
      );
  }
  cancel() {
    this.centerAdded.emit();
  }

  closeConfirmation() {
    this.confirmation = false; // Réinitialise l'indicateur
    this.successMessage = ''; // Réinitialise le message
    this.centerAdded.emit();

  }
}
