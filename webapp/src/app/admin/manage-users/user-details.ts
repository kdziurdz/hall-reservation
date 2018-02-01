import { User } from '../../core/model/user';

export interface UserDetails extends User {
  email: string;
  username: string;
  expirationDate: Date;
  enabled: boolean;
  roles: string[];
}
