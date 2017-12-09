export interface TokenDecoded {
  id: number;
  username: string
  roles: string[];
  expirationDate: Date
}
