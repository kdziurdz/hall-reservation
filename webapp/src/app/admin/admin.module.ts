import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ManageReservationsComponent } from './manage-reservations/manage-reservations.component';
import { AdminService } from './admin.service';

@NgModule({
  declarations: [
    ManageUsersComponent,
    ManageReservationsComponent
  ],
  entryComponents: [
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
