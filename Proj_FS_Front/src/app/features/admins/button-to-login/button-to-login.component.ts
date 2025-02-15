import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventEmitter, Output } from '@angular/core';
import { OnInit } from '@angular/core';
import { AdminMenuComponent } from "../admin-menu/admin-menu.component";

@Component({
  selector: 'app-button-to-login',
  standalone: true,
  imports: [CommonModule, AdminMenuComponent],
  templateUrl: './button-to-login.component.html',
  styleUrls: ['./button-to-login.component.css']
})

export class ButtonToLoginComponent implements OnInit {
  ngOnInit(): void {}

  @Output() showLogin = new EventEmitter<boolean>();
  @Output() onSignout = new EventEmitter<boolean>();


  login_show() {
    // Simule un changement d'état,
    this.showLogin.emit(false); // Informe le parent de cacher <app-search-center>
  }

  // Booléen pour afficher le formulaire de connexion
  login: boolean = false;

  // Booléen pour définir si l'utilisateur est connecté
  isLoggedIn: boolean = false;

  displayLogin() {
    this.login = !this.login;
  }

  // Méthode pour se connecter ( à implémenter avec le service d'authentification )
  signIn() {
    this.isLoggedIn = true;
    this.login = false;
    console.log(`you signed in ! :)`);
  }


  Cancel() {
    this.login = false;
    this.isLoggedIn = false;
    this.onSignout.emit(true);
    console.log('cancel');
  }
}
