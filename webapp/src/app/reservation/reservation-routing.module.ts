import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { MyReservationsComponent } from './my-reservations/my-reservatons.component';

const appRoutes: Routes = [
  {
    path: 'search',
    component: ReservationCreatorComponent
  },
  {
    path: 'my',
    component: MyReservationsComponent
  },
  {
    path: '', redirectTo: 'search', pathMatch: 'full'
  },
  {
    path: '**', redirectTo: 'search' // todo wtf
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ReservationRoutingModule {
}
