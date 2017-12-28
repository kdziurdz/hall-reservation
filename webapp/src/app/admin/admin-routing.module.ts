import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ManageReservationsComponent } from './manage-reservations/manage-reservations.component';
import { SettingsComponent } from './settings/settings.component';

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
    path: 'settings',
    component: SettingsComponent,
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
