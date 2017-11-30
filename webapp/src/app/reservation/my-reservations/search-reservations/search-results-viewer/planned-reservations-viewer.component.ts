import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material';
import { PlannedReservation } from '../../planned-reservations';
import { ReservationService } from '../../../reservation.service';

@Component({
  selector: 'hr-planned-reservations-viewer',
  templateUrl: './planned-reservations-viewer.component.html'
})
export class PlannedReservationsViewerComponent {

  @Input() searchResults: Array<PlannedReservation>;

  constructor(private dialog: MatDialog, private reservationService: ReservationService) {

  }
}
