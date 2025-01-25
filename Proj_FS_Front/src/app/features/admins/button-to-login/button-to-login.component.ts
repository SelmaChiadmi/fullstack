import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventEmitter, Output } from '@angular/core';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-button-to-login',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './button-to-login.component.html',
  styleUrls: ['./button-to-login.component.css']
})

export class ButtonToLoginComponent implements OnInit {
  ngOnInit(): void {}

  @Output() onLogin = new EventEmitter<boolean>();

  login_show() {
    // Simule un changement d'état,
    this.onLogin.emit(false); // Informe le parent de cacher <app-search-center>
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
      console.log(`you signed in ! :)`);
  }

  Cancel() {
    console.log('cancel');
    this.login = false;
    this.onLogin.emit(true);
  }
}
