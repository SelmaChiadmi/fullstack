import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmitterService {

  // Subject pour émettre l'événement
  private eventSource = new Subject<void>();

  // Observable pour écouter l'événement
  event$ = this.eventSource.asObservable();

  // Méthode pour émettre l'événement
  emitEvent(): void {
    this.eventSource.next();  
  }
}
