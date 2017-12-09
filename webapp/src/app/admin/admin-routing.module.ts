import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { ManageReservationsComponent } from './manage-reservations/manage-reservations.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';

const appRoutes: Routes = [
  {
    path: 'manage-users',
    component: ManageUsersComponent
  },
  {
    path: 'manage-reservations',
    component: ManageReservationsComponent,
  },
  {
    path: '', redirectTo: '/manage-users', pathMatch: 'full'
  },
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
