import { Component, Input, Output, EventEmitter } from '@angular/core';
import { VaccinationCenter } from '../../../core/models/vaccination-centers.model';
import { VaccinationCenterService } from '../../../core/services/vaccination-centers.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  standalone: true,
  selector: 'app-edit-center',
  templateUrl: './edit-center.component.html',
  styleUrls: ['./edit-center.component.css'],
  imports: [CommonModule, FormsModule]
})
export class EditCenterComponent {
  @Input() center!: VaccinationCenter; // Centre à modifier
  @Output() centerUpdated = new EventEmitter<VaccinationCenter>(); // Événement pour informer le parent
  iscenterUpdated : boolean = false;

  constructor(private centerService: VaccinationCenterService) {}

 // sauvegarder les modifications dans la base de données
 saveChanges() {
  this.centerService.updateCenter(this.center.id, this.center)
    .subscribe(response => {
      console.log("Mise à jour effectuée :", response);
      this.iscenterUpdated = true;
      
    }, error => {
      console.error("Erreur lors de la mise à jour :", error.status);
      if (error.status === 403) {
        alert("Vous n'êtes pas super-admin");
      }else{
      alert(`Erreur lors de la mise à jour : ${error.message}`);
      }


    });
}

cancel() {
  this.centerUpdated.emit();
}

closeConfirmation(){
  this.iscenterUpdated = false;
  this.centerUpdated.emit();
}
}
