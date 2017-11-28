import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Hall } from '../model/hall';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { AVAILABLE_RESERVATION_DURATION_HOURS } from '../reservation.consts';
import { ReservationService } from '../reservation.service';
import { SearchParams } from './search-form/search-params';
import { AvailableReservation } from '../model/search-result';

@Component({
  selector: 'hr-reservation-creator',
  templateUrl: './reservation-creator.component.html'
})
export class ReservationCreatorComponent {

  searchResults: Array<AvailableReservation> = [];

  constructor(private reservationService: ReservationService) {
  }

  onSearchParamsChanged(params: SearchParams) {
    this.reservationService.searchReservations(params).subscribe(searchResults => {
      this.searchResults = searchResults;
    })
  }

}
