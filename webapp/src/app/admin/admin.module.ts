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

@NgModule({
  declarations: [
    ManageUsersComponent,
    ManageReservationsComponent,
    ChangeExpirationDateDialogComponent,
    RemoveUserConfirmationDialogComponent
  ],
  entryComponents: [
    ChangeExpirationDateDialogComponent,
    RemoveUserConfirmationDialogComponent
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
