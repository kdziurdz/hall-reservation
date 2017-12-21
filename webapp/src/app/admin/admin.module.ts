import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ManageReservationsComponent } from './manage-reservations/manage-reservations.component';
import { AdminService } from './admin.service';
import { ChangeExpirationDateDialogComponent } from './manage-users/dialogs/change-expiration-date-dialog/change-expiration-date-dialog.component';
import { RemoveUserConfirmationDialogComponent } from './manage-users/dialogs/remove-user-confirmation-dialog/remove-user-confirmation-dialog.component';
import { ManageRolesDialogComponent } from './manage-users/dialogs/manage-roles-dialog/manage-roles-dialog.component';
import { ManageRolesComponent } from './manage-users/dialogs/manage-roles.component';
import { CreateUserDialogComponent } from './manage-users/dialogs/create-user-dialog/create-user-dialog.component';
import { ReservationsByHallsComponent } from './manage-reservations/reservations-by-halls/reservations-by-halls.component';
import { ReservationsByHallsSearchFormComponent } from './manage-reservations/reservations-by-halls/reservations-by-halls-search-form/reservations-by-halls-search-form.component';
import { ReservationsByUsersSearchFormComponent } from './manage-reservations/reservations-by-users/reservations-by-users-search-form/reservations-by-users-search-form.component';
import { ReservationsByUsersComponent } from './manage-reservations/reservations-by-users/reservations-by-users.component';

@NgModule({
  declarations: [
    ManageUsersComponent,
    ManageReservationsComponent,
    ChangeExpirationDateDialogComponent,
    RemoveUserConfirmationDialogComponent,
    ManageRolesDialogComponent,
    ManageRolesComponent,
    CreateUserDialogComponent,
    ReservationsByHallsComponent,
    ReservationsByHallsSearchFormComponent,
    ReservationsByUsersSearchFormComponent,
    ReservationsByUsersComponent
  ],
  entryComponents: [
    ChangeExpirationDateDialogComponent,
    RemoveUserConfirmationDialogComponent,
    ManageRolesDialogComponent,
    CreateUserDialogComponent
  ],
  providers: [
    AdminService
  ],
  imports: [
    AdminRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    CommonModule,
    FormsModule
  ],
  exports: []
})
export class AdminModule {

}
