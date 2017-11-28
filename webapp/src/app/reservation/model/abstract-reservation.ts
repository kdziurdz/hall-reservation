export interface AbstractReservationCredentials {
  date: string;
  hallId: number;
}

export abstract class AbstractReservation {
  readonly date: string;
  readonly hallId: number;


  constructor(cred: AbstractReservationCredentials) {
    if(cred) {
      this.date = cred.date;
      this.hallId = cred.hallId;
    }
  }
}
