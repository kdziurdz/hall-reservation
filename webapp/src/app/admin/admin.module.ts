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

@NgModule({
  declarations: [
    ManageUsersComponent,
    ManageReservationsComponent,
    ChangeExpirationDateDialogComponent,
    RemoveUserConfirmationDialogComponent,
    ManageRolesDialogComponent,
    ManageRolesComponent,
    CreateUserDialogComponent
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
