import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { MyReservationsComponent } from './my-reservations/my-reservatons.component';
import { FutureReservationsComponent } from './my-reservations/future-reservations/future-reservations.component';
import { SearchReservationsComponent } from './my-reservations/search-reservations/search-reservations.component';

const appRoutes: Routes = [
  {
    path: 'search',
    component: ReservationCreatorComponent
  },
  {
    path: 'my',
    component: MyReservationsComponent,
    children: [
      {
        path: '',
        redirectTo: 'future',
        pathMatch: 'full',
      },
      {
        path: 'future',
        component: FutureReservationsComponent
      },
      {
        path: 'search',
        component: SearchReservationsComponent
      }
    ]
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
