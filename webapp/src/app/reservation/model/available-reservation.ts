import { AbstractReservation, AbstractReservationCredentials } from './abstract-reservation';
export interface AvailableReservationCredentials extends AbstractReservationCredentials{
  hallName: string;
  lessonNumbers: Array<Array<number>>;
}

export class AvailableReservation extends AbstractReservation{
  readonly hallName;
  readonly lessonNumbers;


  constructor(cred: AvailableReservationCredentials) {
    super(cred);
    if(cred) {
      this.hallName = cred.hallName;
      this.lessonNumbers = cred.lessonNumbers;
    }
  }
}
