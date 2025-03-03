import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { CreateMedecinService } from '../../../core/services/create_medecin.service';
import { Medecin } from '../../../core/models/medecin.model';

@Component({
  selector: 'app-mycenter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [VaccinationCenterService, CreateMedecinService],
  templateUrl: './mycenter.component.html',
  styleUrl: './mycenter.component.css'
})
export class MycenterComponent {
  filteredCenters: any[] = [];
  centers: VaccinationCenter[] = [];

  constructor(private centerService: VaccinationCenterService, private createMedecinService: CreateMedecinService) {}

  ngOnInit(): void {
    this.centerService.centers$.subscribe((data) => {
      this.centers = data;
    });
    this.centerService.loadCenters();
  }

  formData: Medecin = {
    nom: '',
    prenom: '',
    mail: '',
    telephone: 0,
  };

  isOpen = false;


  openForm() {
    this.isOpen = true;
  }

  closeForm() {
    this.isOpen = false;
    this.resetForm();
  }

  
  onSubmit() {
    this.createMedecinService.createMedecin(this.formData).subscribe({
      next: (response) => {
        console.log('Médecin créé avec succès', response);
        alert('Médecin ajouté avec succès !');
        this.resetForm();
        this.closeForm();
      },
      error: (error) => {
        console.error('Erreur lors de la création', error.message);
        alert('Erreur lors de l\'ajout du médecin.' + error.message);
      }
    });
  }

  resetForm() {
    this.formData = { nom: '', prenom: '', mail: '', telephone: 0 };
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
