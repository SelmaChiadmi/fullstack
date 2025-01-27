import { Component, EventEmitter, Output  } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-button-show-list-centers',
  standalone: true,
  imports: [],
  templateUrl: './button-show-list-centers.component.html',
  styleUrl: './button-show-list-centers.component.css'
})
export class ButtonShowListCentersComponent {

  constructor(private router: Router) {}
  @Output() hideOtherComponents = new EventEmitter<void>();

  // Méthode appelée lors du clic sur le bouton pour afficher la liste des centres à l'url '/liste-centres'
  afficherListeCentres(): void {
    this.router.navigate(['/liste-centres']);
    this.hideOtherComponents.emit();
  }

}
