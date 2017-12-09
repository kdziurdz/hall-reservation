import { User, UserCredentials } from './user';
import { Hall, HallCredentials } from '../../reservation/model/hall';
export interface PlannedReservationCredentials {
  id: number;
  lessonNumbers: Array<number>;
  date: string;
  user: UserCredentials;
  canceller: UserCredentials;
  cancelled: boolean;
  cancellationReason: string;
  hall: HallCredentials;
}

export class PlannedReservation {
  readonly id: number;
  readonly lessonNumbers: Array<number>;
  readonly date: string;
  readonly user: User;
  readonly canceller: User;
  readonly cancelled: boolean;
  readonly cancellationReason: string;
  readonly hall: Hall;


  constructor(cred: PlannedReservationCredentials) {
    if(cred){
      this.id = cred.id;
      this.lessonNumbers = cred.lessonNumbers;
      this.date = cred.date;
      this.user = new User(cred.user);
      this.canceller = new User(cred.canceller);
      this.cancelled = cred.cancelled;
      this.cancellationReason = cred.cancellationReason;
      this.hall = new Hall(cred.hall);
    }
  }
}
