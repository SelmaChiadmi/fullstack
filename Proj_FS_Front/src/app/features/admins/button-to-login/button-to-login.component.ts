import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventEmitter, Output } from '@angular/core';
import { OnInit } from '@angular/core';
import { AdminMenuComponent } from "../admin-menu/admin-menu.component";
import { Router } from '@angular/router'
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-button-to-login',
  standalone: true,
  imports: [CommonModule, AdminMenuComponent, FormsModule],
  templateUrl: './button-to-login.component.html',
  styleUrls: ['./button-to-login.component.css']
})

export class ButtonToLoginComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute,private authService: AuthService) {}

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
  /*signIn() {
    this.isLoggedIn = true;
    this.login = false;
    console.log(`you signed in ! :)`);
    this.router.navigate(['/admin/menu']);

    // envoyer le username, password au backend pour vérification (post)
    // le backend appelle authentification (appel au service) 
    // le backend retourne le token jwt au frontend
    // le frontend stocke le token jwt dans le local storage pour l'utiliser dans les requêtes suivantes
    // rediriger vers le menu admin 
    
  }*/

  username : string = '';
  password : string = '';
  errorMessage: string = '';  // Variable pour stocker le message d'erreur

  signIn() {
   
    if (!this.username || !this.password) {
      console.error("Veuillez entrer un nom d'utilisateur et un mot de passe.");
      this.errorMessage = "Veuillez entrer un nom d'utilisateur et un mot de passe.";
      return;
    }
  
    this.authService.login(this.username, this.password).subscribe(
      response => {
        if (response.token) {
          localStorage.setItem('jwt', response.token);
          //console.log("Pour vérifier : Token JWT reçu :", response.token);
          
          this.isLoggedIn = true;
          this.login = false;
          console.log("Connexion réussie !");
          this.router.navigate(['/admin/menu']); // Redirection après connexion
        }
      },
      error => {
        console.error("Erreur d'authentification : ", error);
        this.errorMessage = "Nom d'utilisateur ou mot de passe incorrect.";  // Affichage du message d'erreur
      }
    );
  }
  


  Cancel() {
    this.login = false;
    this.isLoggedIn = false;
    this.onSignout.emit(true);
    console.log('cancel');
    this.router.navigate(['/public'])
  }
  
}
