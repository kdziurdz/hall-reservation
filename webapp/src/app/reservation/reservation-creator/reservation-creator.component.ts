import { Component } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { SearchParams } from './search-form/search-params';
import { AvailableReservation } from '../model/available-reservation';

@Component({
  selector: 'hr-reservation-creator',
  templateUrl: './reservation-creator.component.html'
})
export class ReservationCreatorComponent {

  searchResults: Array<AvailableReservation>;

  constructor(private reservationService: ReservationService) {
  }

  onSearchParamsChanged(params: SearchParams) {
    this.reservationService.searchReservations(params).subscribe(searchResults => {
      this.searchResults = searchResults;
    });
  }
}
