export interface AvailableReservationCredentials {
  date: string;
  hallId: number;
  hallName: string;
  lessonNumbers: Array<Array<number>>;
}

export class AvailableReservation {
  readonly date;
  readonly hallId;
  readonly hallName;
  readonly lessonNumbers;


  constructor(cred: AvailableReservationCredentials) {
    if(cred) {
      this.date = cred.date;
      this.hallId = cred.hallId;
      this.hallName = cred.hallName;
      this.lessonNumbers = cred.lessonNumbers;
    }
  }
}
