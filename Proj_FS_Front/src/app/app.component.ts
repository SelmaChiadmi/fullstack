import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SearchCenterComponent } from "./features/search-center/search-center.component";
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SearchCenterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Faites vous vacciner !';
  

}
