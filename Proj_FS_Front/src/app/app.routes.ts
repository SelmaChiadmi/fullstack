import { Routes } from '@angular/router';
import {AdminMenuComponent} from './features/admins/admin-menu/admin-menu.component';
import { CentresComponent } from './features/admins/centres/centres.component';
import { ButtonToLoginComponent } from './features/admins/button-to-login/button-to-login.component';
import { SearchCenterComponent } from './features/reservations/search-center/search-center.component';
import { SuperAdminsComponent } from './features/admins/super-admins/super-admins.component';
import { PlanningComponent } from './features/admins/planning/planning.component';
import { MycenterComponent } from './features/admins/mycenter/mycenter.component';
import { ReservationsComponent } from './features/admins/reservations/reservations.component';
import { ConfirmRdvComponent } from './features/reservations/confirm-rdv/confirm-rdv.component';
import { Component } from '@angular/core';

export const routes: Routes = [
    {path: 'public',
        children: [
            { path: '', component: SearchCenterComponent },
        ]
    },

    {path: 'login', component: ButtonToLoginComponent},
    { path: 'admin/menu', component: AdminMenuComponent,
        children: [
        { path: 'centres', component: CentresComponent },
        { path: 'superadmins', component : SuperAdminsComponent},
        { path: 'planning', component : PlanningComponent},
        { path: 'mycenter', component : MycenterComponent},
        { path: 'reservations', component :ReservationsComponent},
          ]
    },
      
      

];
