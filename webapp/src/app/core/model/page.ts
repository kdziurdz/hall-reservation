export interface PageCredentials<T> {
  last: boolean;
  first: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  sort: string | null;
  numberOfElements: number;
  content: Array<T>;
}

export class Page<T> {
  readonly last: boolean;
  readonly first: boolean;
  readonly totalElements: number;
  readonly totalPages: number;
  readonly size: number;
  readonly number: number;
  readonly sort: string | null;
  readonly numberOfElements: number;
  readonly content: Array<T>;

  constructor(cred: PageCredentials<T>) {
    if(cred) {
      this.last = cred.last;
      this.first = cred.first;
      this.totalElements = cred.totalElements;
      this.totalPages = cred.totalPages;
      this.size = cred.size;
      this.number = cred.number;
      this.sort = cred.sort;
      this.numberOfElements = cred.numberOfElements;
      this.content = cred.content;
    }
  }
}
