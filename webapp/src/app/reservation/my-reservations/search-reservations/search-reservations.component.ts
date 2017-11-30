import { Component } from '@angular/core';
import { PlannedReservationSearchParams } from './planned-reservation-search-form/planned-reservation-search-params';
import { ReservationService } from '../../reservation.service';
import { PlannedReservation } from '../planned-reservations';
import { Page } from '../../../core/model/page';

@Component({
  selector: 'hr-search-reservations',
  templateUrl: './search-reservations.component.html'
})
export class SearchReservationsComponent {

  searchResults: Page<PlannedReservation>;

  constructor(private reservationService: ReservationService) {
  }

  onSearchParamsChanged(params: PlannedReservationSearchParams) {
    this.reservationService.searchPlannedReservations(params).subscribe(searchResults => {
      this.searchResults = searchResults;
    });
  }
}
