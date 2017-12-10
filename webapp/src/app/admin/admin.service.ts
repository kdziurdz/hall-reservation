import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PlannedReservationSearchParams } from '../core/model/planned-reservation-search-params';
import { Observable } from 'rxjs/Observable';
import { Page } from '../core/model/page';
import { PlannedReservation } from '../core/model/planned-reservations';
import { PlannedReservationSearchWithUserIdParams } from './model/planned-reservation-search-with-user-id-params';
import { SearchUsersParams } from './manage-users/search-users-params';
import { UserDetails } from './manage-users/user-details';

export const ADMIN_URL = 'api/admin';
export const USERS_URL = 'api/user';

@Injectable()
export class AdminService {

  constructor(private httpClient: HttpClient) {

  }

  searchPlannedReservations(searchParams: PlannedReservationSearchWithUserIdParams): Observable<Page<PlannedReservation>> {
    let params: HttpParams = new HttpParams();

    params = params.set('dateFrom', searchParams.dateFrom);
    params = params.set('dateTo', searchParams.dateTo);
    if (searchParams.hallIds) {
      params = params.set('hallIds', searchParams.hallIds.toString());
    }
    if (searchParams.userId) {
      params = params.set('userId', searchParams.userId.toString());
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
    params = params.set('status', searchParams.status.toString());
    return this.httpClient.get<Page<PlannedReservation>>(`${ADMIN_URL}`, {params: params});
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
}
