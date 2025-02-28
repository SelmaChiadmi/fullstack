import { Routes } from '@angular/router';
import {AdminMenuComponent} from './features/admins/admin-menu/admin-menu.component';
import { CentresComponent } from './features/admins/centres/centres.component';
import { ButtonToLoginComponent } from './features/admins/button-to-login/button-to-login.component';
import { SearchCenterComponent } from './features/reservations/search-center/search-center.component';
import { SuperAdminsComponent } from './features/admins/super-admins/super-admins.component';
import { PlanningComponent } from './features/admins/planning/planning.component';
import { MycenterComponent } from './features/admins/mycenter/mycenter.component';
import { AuthGuard } from './auth.guard';


export const routes: Routes = [

    {path: '', redirectTo: 'public', pathMatch: 'full'},
    {path: 'public',
        children: [
            { path: '', component: SearchCenterComponent },
        ]
    },


    {path: 'login', component: ButtonToLoginComponent},
    { path: 'admin/menu', component: AdminMenuComponent, canActivate: [AuthGuard],
        children: [
        { path: 'centres', component: CentresComponent, canActivate: [AuthGuard] },
        { path: 'superadmins', component : SuperAdminsComponent, canActivate: [AuthGuard]},
        { path: 'planning', component : PlanningComponent, canActivate: [AuthGuard]},
        { path: 'mycenter', component : MycenterComponent, canActivate: [AuthGuard]},
          ]
    },
      
      

];
