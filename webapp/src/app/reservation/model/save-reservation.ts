import { AbstractReservation, AbstractReservationCredentials } from './abstract-reservation';
export interface SaveReservationCredentials extends AbstractReservationCredentials{
  lessonNumbers: Array<number>;
}

export class SaveReservation extends AbstractReservation{
  readonly lessonNumbers: Array<number>;


  constructor(cred: SaveReservationCredentials) {
    super(cred);
    if(cred) {
      this.lessonNumbers = cred.lessonNumbers;
    }
  }
}
