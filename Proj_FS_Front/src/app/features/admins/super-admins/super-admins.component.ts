import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SuperAdminService } from '../../../core/services/super-admins.service';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';

@Component({
  selector: 'app-super-admins',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './super-admins.component.html',
  styleUrl: './super-admins.component.css'
})
export class SuperAdminsComponent {

  superAdmins: any[] = [];  // Liste des super administrateurs
  isLoading: boolean = false;
  errorMessage: string = '';
  isConfirmationDisplayed: boolean = false;  // Affichage de la confirmation
  superAdminToDelete: any = null;  // Super administrateur à supprimer
  searchQuery: string = '';
  filteredCenters: any[] = [];
  centers: VaccinationCenter[] = [];
  centreId: number = 4; // ID du centre qui représente le centre de vaccination "Aucun"

  constructor(@Inject(SuperAdminService) private superAdminService: SuperAdminService, private centerService: VaccinationCenterService) {}

  ngOnInit(): void {
    this.loadSuperAdmins();  // Charger les super administrateurs lors de l'initialisation du composant
    this.centerService.centers$.subscribe((data) => {
      this.centers = data;
    });
    this.centerService.loadCenters();
  }

  loadSuperAdmins(): void {
    this.isLoading = true;
    this.superAdminService.getSuperAdmins().subscribe({
      next: (data) => {
        this.superAdmins = data;  // Stocker la liste des super administrateurs
        console.log('Super admins:', this.superAdmins);
        this.isLoading = false;  // Arrêter le chargement
      },
      error: (error) => {
        if (error.status === 403) {
          alert("Vous n'êtes pas super-admin");
        }

        this.isLoading = false;  // Arrêter le chargement en cas d'erreur
        console.error('Erreur:', error);
      }
    });
  }

  // Méthode pour afficher la confirmation avant suppression
  deleteSuperadmin(superAdmin: any): void {
    this.superAdminToDelete = superAdmin;
    this.isConfirmationDisplayed = true;
    
  }

  // Confirmer la suppression
  confirmDelete(): void {
    const superAdminMail = this.superAdminToDelete.mail;
      // Fonction pour appeler la suppression d'un super administrateur
      this.superAdminService.deleteSuperAdmin(superAdminMail).subscribe({
        next: () => {
          console.log('Super Admin supprimé avec succès');
          this.loadSuperAdmins();
        },
        error: (err) => {
          if (err.status === 308) {
            alert("Vous ne pouvez pas supprimer le super-admin avec lequel vous êtes connecté");
          }else{
          console.error('Erreur lors de la suppression du Super Admin', err);
          alert('Erreur lors de la suppression du Super Admin: ' + err.message);
        }},
    });
  
    console.log('Supprimer super admin:', this.superAdminToDelete);
    // Rechargez la liste après suppression
   
    this.closeConfirmation();
  }

  // Annuler la suppression
  closeConfirmation(): void {
    this.isConfirmationDisplayed = false;
    this.superAdminToDelete = null;
  }


  isOpen = false;

  formData = {
    nom: '',
    prenom: '',
    mail: '',
    telephone: 0,
  };

  openForm() {
    this.isOpen = true;
  }

  closeForm() {
    this.isOpen = false;
    this.resetForm();
  }

  onSubmit() {
    
    this.superAdminService.createSuperAdmin(this.formData, this.centreId).subscribe({
      next: (response) => {
        console.log('Super administrateur créé avec succès', response);
        alert('Super administrateur créé avec succès !');
      },
      error: (error) => {
        console.error('Erreur lors de la création du super administrateur', error.message);
        alert('Erreur lors de la création du super administrateur: ' + error.message);
        this.errorMessage = 'Erreur lors de la création du super administrateur: ' + error.message;
      },});
    console.log('Nouvel employé :', this.formData);
    this.closeForm(); // Ferme le formulaire après soumission
  }

  // chercher un centre
  filterCenters() {
    this.filteredCenters = this.centers.filter(center =>
      center.nom.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }


  resetForm() {
    this.formData = {
      nom: '',
      prenom: '',
      mail: '',
      telephone: 0,
    };
  }


}



