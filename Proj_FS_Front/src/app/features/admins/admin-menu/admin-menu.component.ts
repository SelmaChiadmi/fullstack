import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { jwtDecode } from 'jwt-decode';


@Component({
  selector: 'app-admin-menu',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './admin-menu.component.html',
  styleUrl: './admin-menu.component.css'
})
export class AdminMenuComponent {

  constructor(private router: Router) {}

  displayCentres: boolean = false;
  displayMyCenter: boolean = false;
  displayPlanning: boolean = false;
  displayConfig: boolean = false;
  role = '';

  ngOnInit() {
    const token = localStorage.getItem('jwt');
    if (token) {
      const decodedToken: any = jwtDecode(token);
      const roles = decodedToken.roles;
      this.role = roles[0].authority;
      //console.log('Rôle de l\'utilisateur :', this.role);
      //console.log('Type de this.role :', typeof this.role);
    }
    this.DisplayCentres();
    this.DisplayMyCenter();
    this.DisplayPlanning();
    this.DisplayConfig();
    console.log('displayCentres :', this.displayCentres);
    console.log('displayMyCenter :', this.displayMyCenter);
    console.log('displayPlanning :', this.displayPlanning);
    console.log('displayConfig :', this.displayConfig);
    

  }

  // la partie centres : roles super-admin 
  DisplayCentres(){
    if (this.role === 'ROLE_SUPER_ADMIN') {
      this.displayCentres = true;
    } else {
      this.displayCentres = false;

    }
  }

  // la partie mon centre : roles admin
  DisplayMyCenter(){
    if (this.role === 'ROLE_ADMIN') {
      this.displayMyCenter = true;
    } else {
      this.displayMyCenter = false;

  }
}

  //la partie planning : roles admin et médecin
  DisplayPlanning(){
    if (this.role === 'ROLE_ADMIN' || this.role === 'ROLE_MEDECIN') {
      this.displayPlanning = true;
    } else {
      
      this.displayPlanning = false;
    }
  }

  // la partie config : roles super-admin
  DisplayConfig(){
    if (this.role === 'ROLE_SUPER_ADMIN') {
      this.displayConfig = true;
    } else {
      this.displayConfig = false;
    }
  }

  

  navigateTo(page: string) {
    console.log('Navigating to', page);
    this.router.navigate([`/admin/menu/${page}`]);
  }

  Cancel() {
    console.log('cancel');
    this.router.navigate(['/public'])
    // suppression du token du localStorage
    localStorage.removeItem('jwt');
  }

}
