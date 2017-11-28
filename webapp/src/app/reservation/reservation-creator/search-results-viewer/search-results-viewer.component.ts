import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AvailableReservation } from '../../model/available-reservation';
import {
  LESSON_END_IDENTIFIER_PREFIX,
  LESSON_NUMBER_TIME_END,
  LESSON_NUMBER_TIME_START,
  LESSON_START_IDENTIFIER_PREFIX
} from '../../reservation.consts';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ReservationConfirmationDialog } from './reservation-confirmation-dialog/reservation-confirmation-dialog.component';
import { SaveReservation } from '../../model/save-reservation';


@Component({
  selector: 'hr-search-results-viewer',
  templateUrl: './search-results-viewer.component.html'
})
export class SearchResultsViewerComponent {

  @Input() searchResults: Array<AvailableReservation> = [];
  @Output() onReservationCommitted: EventEmitter<SaveReservation> = new EventEmitter();

  private timeStart = LESSON_NUMBER_TIME_START;
  private timeEnd = LESSON_NUMBER_TIME_END;

  constructor(private dialog: MatDialog) {

  }

  getLessonNumberStart(lessonNumber: number) {
    return this.timeStart[LESSON_START_IDENTIFIER_PREFIX + lessonNumber];
  }

  getLessonNumberEnd(lessonNumber: number) {
    return this.timeEnd[LESSON_END_IDENTIFIER_PREFIX + lessonNumber];
  }

  onAvailableReservationChosen(availableReservation: AvailableReservation, chosenLessonNumbers: number[]) {
    //console.log('elo')
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {date: availableReservation.date, hours: this.getReservationTimeAsString(chosenLessonNumbers)};
    conf.width = '300px';


    let dialogRef = this.dialog.open(ReservationConfirmationDialog, conf);

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.onReservationCommitted.emit(new SaveReservation({
          hallId: availableReservation.hallId,
          date: availableReservation.date, lessonNumbers: chosenLessonNumbers
        }));
      }
    });
  }

  getReservationTimeAsString(chosenLessonNumbers: number[]) {
    return this.getLessonNumberStart(chosenLessonNumbers[0]) + ' - ' + this.getLessonNumberEnd(chosenLessonNumbers[chosenLessonNumbers.length - 1]);
  }

}
