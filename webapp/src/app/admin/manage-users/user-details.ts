import { User } from '../../core/model/user';

export interface UserDetails extends User {
  email: string;
  expirationDate: Date;
  enabled: boolean
}
