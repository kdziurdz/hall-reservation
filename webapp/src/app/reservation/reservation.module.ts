import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { NgModule } from '@angular/core';
import { ReservationRoutingModule } from './reservation-routing.module';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservationService } from './reservation.service';

@NgModule({
  declarations: [
    ReservationCreatorComponent
  ],
  providers: [
    ReservationService
  ],
  imports: [
    ReservationRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    CommonModule,
    FormsModule
  ],
  exports: []
})
export class ReservationModule {

}
