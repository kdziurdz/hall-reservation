import { Injectable } from '@angular/core';
import 'rxjs/add/operator/do';
import {
  LESSON_END_IDENTIFIER_PREFIX, LESSON_NUMBER_TIME_END, LESSON_NUMBER_TIME_START,
  LESSON_START_IDENTIFIER_PREFIX
} from '../reservation.consts';


@Injectable()
export class LessonDateTimeService {

  private timeStart = LESSON_NUMBER_TIME_START;
  private timeEnd = LESSON_NUMBER_TIME_END;

  getLessonNumberStart(lessonNumber: number) {
    return this.timeStart[LESSON_START_IDENTIFIER_PREFIX + lessonNumber];
  }

  getLessonNumberEnd(lessonNumber: number) {
    return this.timeEnd[LESSON_END_IDENTIFIER_PREFIX + lessonNumber];
  }

  getReservationTimeAsString(chosenLessonNumbers: number[]) {
    return this.getLessonNumberStart(chosenLessonNumbers[0]) + ' - ' + this.getLessonNumberEnd(chosenLessonNumbers[chosenLessonNumbers.length - 1]);
  }

}
