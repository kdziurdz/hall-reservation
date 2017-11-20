import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';

const appRoutes: Routes = [
  { path: '', component: ReservationCreatorComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ReservationRoutingModule {}
