import { ReservationCreatorComponent } from './reservation-creator/reservation-creator.component';
import { NgModule } from '@angular/core';
import { ReservationRoutingModule } from './reservation-routing.module';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservationService } from './reservation.service';
import { SearchFormComponent } from './reservation-creator/search-form/search-form.component';
import { SearchResultsViewerComponent } from './reservation-creator/search-results-viewer/search-results-viewer.component';
import { ReservationConfirmationDialog } from './reservation-creator/search-results-viewer/reservation-confirmation-dialog/reservation-confirmation-dialog.component';
import { MyReservationsComponent } from './my-reservations/my-reservatons.component';
import { ReservationListComponent } from './my-reservations/reservation-list/reservation-list.component';
import { FutureReservationsComponent } from './my-reservations/future-reservations/future-reservations.component';
import { PlannedReservationSearchFormComponent } from './my-reservations/search-reservations/planned-reservation-search-form/planned-reservation-search-form.component';
import { PlannedReservationsViewerComponent } from './my-reservations/search-reservations/planned-reservations-viewer.component';

@NgModule({
  declarations: [
    ReservationCreatorComponent,
    SearchFormComponent,
    SearchResultsViewerComponent,
    ReservationConfirmationDialog,
    MyReservationsComponent,
    ReservationListComponent,
    FutureReservationsComponent,
    PlannedReservationSearchFormComponent,
    PlannedReservationsViewerComponent
  ],
  entryComponents: [
    ReservationConfirmationDialog
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
