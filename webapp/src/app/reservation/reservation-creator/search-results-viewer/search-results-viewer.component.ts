import { Component, Input } from '@angular/core';
import { AvailableReservation } from '../../model/search-result';
import {
  LESSON_END_IDENTIFIER_PREFIX,
  LESSON_NUMBER_TIME_END, LESSON_NUMBER_TIME_START,
  LESSON_START_IDENTIFIER_PREFIX
} from '../../reservation.consts';


@Component({
  selector: 'hr-search-results-viewer',
  templateUrl: './search-results-viewer.component.html',
  styleUrls: ['./search-results-viewer.component.scss']
})
export class SearchResultsViewerComponent {

  private timeStart = LESSON_NUMBER_TIME_START;
  private timeEnd = LESSON_NUMBER_TIME_END;

  @Input() searchResults: Array<AvailableReservation> = [];

  getLessonNumberStart(lessonNumber: number){
    return this.timeStart[LESSON_START_IDENTIFIER_PREFIX + lessonNumber];
  }

  getLessonNumberEnd(lessonNumber: number){
    return this.timeEnd[LESSON_END_IDENTIFIER_PREFIX + lessonNumber];
  }

}
