import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Page } from '../core/model/page';
import { SearchUsersParams } from './manage-users/search-users-params';
import { UserDetails } from './manage-users/user-details';
import { CreateUserDialogCreds } from './manage-users/dialogs/create-user-dialog/create-user-dialog-result';
import { User } from '../core/model/user';
import { ClassesPeriod } from './settings/classes-period';
import { CreateClassesPeriodDialogResult } from './settings/create-classes-period-dialog/create-classes-period-dialog-result';

export const CLASSES_PERIODS_URL = 'api/classes-period';
export const USERS_URL = 'api/user';

@Injectable()
export class AdminService {

  constructor(private httpClient: HttpClient) {

  }

  searchUsers(searchParams: SearchUsersParams): Observable<Page<UserDetails>> {
    let params: HttpParams = new HttpParams();

    if (searchParams.query) {
      params = params.set('query', searchParams.query);
    }
    if (searchParams.sort) {
      params = params.set('sort', searchParams.sort);
    }
    if (searchParams.pageNumber) {
      params = params.set('page', String(searchParams.pageNumber));
    }
    if (searchParams.pageSize) {
      params = params.set('size', String(searchParams.pageSize));
    }
    return this.httpClient.get<Page<UserDetails>>(`${USERS_URL}`, {params: params});
  }

  enable(userId: number) {
    return this.httpClient.patch(`${USERS_URL}/${userId}/enable`, null);
  }

  disable(userId: number) {
    return this.httpClient.patch(`${USERS_URL}/${userId}/disable`, null);
  }

  remove(userId: number) {
    return this.httpClient.patch(`${USERS_URL}/${userId}/remove`, null);
  }

  setExpirationDate(newDate: string, userId: number) {
    let params: HttpParams = new HttpParams();

    if (newDate) {
      params = params.set('expirationDate', newDate);
    }
    return this.httpClient.patch(`${USERS_URL}/${userId}/expirationDate`, null,
      {params: params});
  }

  updateRoles(roles: string[], userId: number) {
    return this.httpClient.patch(`${USERS_URL}/${userId}/roles`, roles);
  }

  createUser(createUserCreds: CreateUserDialogCreds) {
    return this.httpClient.post(`${USERS_URL}`, createUserCreds);
  }

  querySearchUsers(query: any): Observable<Array<User>> {
    if (query) {
      return this.httpClient.get<Array<User>>(`${USERS_URL}/search?query=${query}`);
    }
  }

  getClassesPeriods(): Observable<Array<ClassesPeriod>>{
    return this.httpClient.get<Array<ClassesPeriod>>(CLASSES_PERIODS_URL);
  }

  removeClassPeriod(id: number): Observable<any> {
    return this.httpClient.delete(`${CLASSES_PERIODS_URL}/${id}`)
  }

  createClassesPeriod(result: CreateClassesPeriodDialogResult): Observable<any> {
    return this.httpClient.post(CLASSES_PERIODS_URL, result);
  }
}
