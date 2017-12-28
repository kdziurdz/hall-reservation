import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ManageReservationsComponent } from './manage-reservations/manage-reservations.component';
import { ClassesPeriodsComponent } from './classes-periods/classes-periods.component';

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
    path: 'classes-periods',
    component: ClassesPeriodsComponent,
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
