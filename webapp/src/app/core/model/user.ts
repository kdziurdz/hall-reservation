export interface UserCredentials {
  id: number;
  firstName: string;
  lastName: string;
}

export class User {
  readonly id: number;
  readonly firstName: string;
  readonly lastName: string;

  constructor(cred: UserCredentials) {
    if(cred) {
      this.id = cred.id;
      this.firstName = cred.firstName;
      this.lastName = cred.lastName;
    }
  }
}
