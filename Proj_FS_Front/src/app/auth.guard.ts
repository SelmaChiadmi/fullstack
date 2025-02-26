import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    
    // Vérifier si un token JWT est stocké dans le localStorage
    const token = localStorage.getItem('jwt');
    
    // Si le token existe, l'utilisateur est authentifié, on autorise l'accès
    if (token) {
      return true;
    }

    // Si pas de token, rediriger l'utilisateur vers la page de connexion
    this.router.navigate(['/login']);
    return false;
  }
}
