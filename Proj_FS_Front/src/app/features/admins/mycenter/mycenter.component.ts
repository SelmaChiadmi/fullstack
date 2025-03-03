import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';

@Component({
  selector: 'app-mycenter',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './mycenter.component.html',
  styleUrl: './mycenter.component.css'
})
export class MycenterComponent {
  searchQuery: string = '';
  filteredCenters: any[] = [];
  centers: VaccinationCenter[] = [];

  constructor(private centerService: VaccinationCenterService) {}

  ngOnInit(): void {
    this.centerService.centers$.subscribe((data) => {
      this.centers = data;
    });
    this.centerService.loadCenters();
  }

  isOpen = false;
  formData = {
    nom: '',
    prenom: '',
    email: '',
    password: '',
    centre: ''
  };

  openForm() {
    this.isOpen = true;
  }

  closeForm() {
    this.isOpen = false;
    this.resetForm();
  }

  onSubmit() {
    console.log('Nouvel employé :', this.formData);
    this.closeForm(); // Ferme le formulaire après soumission
  }

  // chercher un centre
  filterCenters() {
    this.filteredCenters = this.centers.filter(center =>
      center.nom.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  // sélectionner un centre
  selectCenter(nom: string) {
    this.formData.centre = nom;
    this.searchQuery = nom;
    this.filteredCenters = []; // Cache la liste après sélection
  }

  private resetForm() {
    this.formData = {
      nom: '',
      prenom: '',
      email: '',
      password: '',
      centre: ''
    };
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
