import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageReservationsComponent } from './manage-reservations/manage-reservations.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ReservationsByUsersComponent } from './manage-reservations/reservations-by-users/reservations-by-users.component';
import { ReservationsByHallsComponent } from './manage-reservations/reservations-by-halls/reservations-by-halls.component';

const appRoutes: Routes = [
  {
    path: 'manage-users',
    component: ManageUsersComponent
  },
  {
    path: 'manage-reservations',
    component: ManageReservationsComponent,
    children: [
      {
        path: '',
        redirectTo: 'by-halls',
        pathMatch: 'full'
      },
      {
        path: 'by-halls',
        component: ReservationsByHallsComponent
      },
      {
        path: 'by-users',
        component: ReservationsByUsersComponent
      }
    ]
  },
  {
    path: '', redirectTo: 'manage-users', pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdminRoutingModule {
}
