import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-admin-menu',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './admin-menu.component.html',
  styleUrl: './admin-menu.component.css'
})
export class AdminMenuComponent {

  constructor(private router: Router) {}

  navigateTo(page: string) {
    console.log('Navigating to', page);
  }
}
