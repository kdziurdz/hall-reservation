import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

const appRoutes: Routes = [
  {path: '', redirectTo: '/reservation', pathMatch: 'full'},
  {path: 'reservation', loadChildren: 'app/reservation/reservation.module#ReservationModule', pathMatch: 'full'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes, {enableTracing: true} // <-- debugging purposes only

    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
