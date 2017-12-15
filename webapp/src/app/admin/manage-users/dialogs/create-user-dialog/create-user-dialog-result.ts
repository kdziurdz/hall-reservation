export interface CreateUserDialogCreds {
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  roles: string[];
  expirationDate: string | Date;
  enabled: string;
  email: string;
}
