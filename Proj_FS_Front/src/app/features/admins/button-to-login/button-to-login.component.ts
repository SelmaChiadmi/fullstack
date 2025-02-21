import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventEmitter, Output } from '@angular/core';
import { OnInit } from '@angular/core';
import { AdminMenuComponent } from "../admin-menu/admin-menu.component";
import { Router } from '@angular/router'
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-button-to-login',
  standalone: true,
  imports: [CommonModule, AdminMenuComponent],
  templateUrl: './button-to-login.component.html',
  styleUrls: ['./button-to-login.component.css']
})

export class ButtonToLoginComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute) {}

  login: boolean = false;

  ngOnInit(): void {
    // Vérifie si l'utilisateur est sur la page "/login"
    this.route.url.subscribe(urlSegments => {
      this.login = urlSegments.length > 0 && urlSegments[0].path === 'login';
    });
  }

    

  @Output() showLogin = new EventEmitter<boolean>();
  @Output() onSignout = new EventEmitter<boolean>();


  login_show() {
    // Simule un changement d'état,
    this.showLogin.emit(false); // Informe le parent de cacher <app-search-center>
  }

  

  // Booléen pour définir si l'utilisateur est connecté
  isLoggedIn: boolean = false;

  displayLogin() {
    this.login = true;
    this.router.navigate(['/login'])
  }

  // Méthode pour se connecter ( à implémenter avec le service d'authentification )
  signIn() {
    this.isLoggedIn = true;
    this.login = false;
    console.log(`you signed in ! :)`);
    this.router.navigate(['/admin/menu']);
  }


  Cancel() {
    this.login = false;
    this.isLoggedIn = false;
    this.onSignout.emit(true);
    console.log('cancel');
    this.router.navigate(['/public'])
  }
  
}
