import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { NgModule } from '@angular/core';
import { ReservationRoutingModule } from './reservation-routing.module';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    ReservationCreatorComponent
  ],
  providers: [
  ],
  imports: [
    ReservationRoutingModule,
    SharedModule
  ],
  exports: []
})
export class ReservationModule {

}
