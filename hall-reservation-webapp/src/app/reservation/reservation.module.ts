import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { NgModule } from '@angular/core';
import { ReservationRoutingModule } from './reservation-routing.module';

@NgModule({
  declarations: [
    ReservationCreatorComponent
  ],
  providers: [
  ],
  imports: [
    ReservationRoutingModule
  ],
  exports: []
})
export class ReservationModule {

}
