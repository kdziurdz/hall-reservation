export interface HallCredentials {
  name: string;
  id: number;
}

export class Hall {
  readonly name: string;
  readonly id: number;

  constructor(cred: HallCredentials) {
    if(cred) {
      this.name = cred.name;
      this.id = cred.id;
    }
  }
}
