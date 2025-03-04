import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { MedecinService } from '../../../core/services/medecin.service';
import { Employe } from '../../../core/models/employe.model';

@Component({
  selector: 'app-mycenter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [VaccinationCenterService, MedecinService],
  templateUrl: './mycenter.component.html',
  styleUrl: './mycenter.component.css'
})
export class MycenterComponent {
  filteredCenters: any[] = [];
  centers: VaccinationCenter[] = [];
  medecins: any[] = [];
  errorMessage: string = '';
  centre: any = null;
  

  constructor(private centreService: VaccinationCenterService, private centreService1: MedecinService ) {}

  ngOnInit(): void {
    this.loadMedecins();
    this.loadCentre();
  }

  loadCentre(): void {
    this.centreService.getCentreByAdmin().subscribe({
      next: (data) => {
        this.centre = data;
      },
      error: (error) => {
        if (error.status === 403) {
          alert("Vous n'êtes pas admin");
        }else{
        this.errorMessage = 'Erreur lors du chargement du centre.';
        console.error(error);}
      }
    });
  }

  loadMedecins(): void {
    this.centreService1.getMedecins().subscribe({
      next: (data) => {
        this.medecins = data;
      },
      error: (error) => {
        if (error.status === 403) {
          console.log("Vous n'êtes pas admin : vous ne pouvez pas voir les médecins");
        }else{
        this.errorMessage = 'Erreur lors du chargement des médecins.';
        console.error(error);}
      }
    });
  }

  deleteMedecin(email: string): void {
    if (confirm(`Voulez-vous vraiment supprimer le médecin avec l'email : ${email} ?`)) {
      this.centreService1.deleteMedecin(email).subscribe({
        next: () => {
          this.medecins = this.medecins.filter(m => m.mail !== email);
        },
        error: (error) => {
          this.errorMessage = error.message;
          alert('Erreur lors de la suppression du médecin.'+ error.message);
          console.error(error);
        }
      });
    }
  }

  

  formData: Employe = {
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
    this.centreService1.createMedecin(this.formData).subscribe({
      next: (response) => {
        console.log('Médecin créé avec succès', response);
        alert('Médecin ajouté avec succès !');
        this.resetForm();
        this.closeForm();
      },
      error: (error) => {
        if (error.status === 403) {
          alert("Vous n'êtes pas admin");
        }else{
        console.error('Erreur lors de la création', error.message);
        alert('Erreur lors de l\'ajout du médecin.' + error.message);}
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
