import { Component, Input } from '@angular/core';
import { AvailableReservation } from '../../model/available-reservation';
import {
  LESSON_END_IDENTIFIER_PREFIX,
  LESSON_NUMBER_TIME_END,
  LESSON_NUMBER_TIME_START,
  LESSON_START_IDENTIFIER_PREFIX
} from '../../../core/reservation.consts';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ReservationConfirmationDialog } from './reservation-confirmation-dialog/reservation-confirmation-dialog.component';
import { SaveReservation } from '../../model/save-reservation';
import { ReservationService } from '../../reservation.service';
import { LessonDateTimeService } from '../../../core/service/lesson-date-time.service';


@Component({
  selector: 'hr-search-results-viewer',
  templateUrl: './search-results-viewer.component.html'
})
export class SearchResultsViewerComponent {

  @Input() searchResults: Array<AvailableReservation>;

  constructor(private dialog: MatDialog, private reservationService: ReservationService,
              private lessonDateTimeService: LessonDateTimeService) {
  }

  getReservationTimeAsString(chosenLessonNumbers: number[]){
    this.lessonDateTimeService.getReservationTimeAsString(chosenLessonNumbers);
  }


  onAvailableReservationChosen(availableReservation: AvailableReservation, chosenLessonNumbers: number[]) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {date: availableReservation.date,
      hours: this.lessonDateTimeService.getReservationTimeAsString(chosenLessonNumbers)};
    conf.width = '300px';


    let dialogRef = this.dialog.open(ReservationConfirmationDialog, conf);

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.reservationService.reserve((new SaveReservation({
          hallId: availableReservation.hallId,
          date: availableReservation.date, lessonNumbers: chosenLessonNumbers
        }))).subscribe(() => {
          availableReservation.lessonNumbers.splice(availableReservation.lessonNumbers.indexOf(chosenLessonNumbers), 1);
        });
      }
    });
  }


}
