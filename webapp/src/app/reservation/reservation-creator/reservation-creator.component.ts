import { Component } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { AvailableReservationSlotSearchParams } from './search-form/available-reservation-slot-search-params';
import { AvailableReservation } from '../model/available-reservation';

@Component({
  selector: 'hr-reservation-creator',
  templateUrl: './reservation-creator.component.html'
})
export class ReservationCreatorComponent {

  searchResults: Array<AvailableReservation>;

  constructor(private reservationService: ReservationService) {
  }

  onSearchParamsChanged(params: AvailableReservationSlotSearchParams) {
    this.reservationService.searchAvailableReservationSlots(params).subscribe(searchResults => {
      this.searchResults = searchResults;
    });
  }
}
