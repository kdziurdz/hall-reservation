import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { NgModule } from '@angular/core';
import { ReservationRoutingModule } from './reservation-routing.module';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    ReservationCreatorComponent
  ],
  providers: [
  ],
  imports: [
    ReservationRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    CommonModule
  ],
  exports: []
})
export class ReservationModule {

}
