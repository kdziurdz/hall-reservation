import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PlannedReservationSearchParams } from '../core/model/planned-reservation-search-params';
import { Observable } from 'rxjs/Observable';
import { Page } from '../core/model/page';
import { PlannedReservation } from '../core/model/planned-reservations';
import { PlannedReservationSearchWithUserIdParams } from './model/planned-reservation-search-with-user-id-params';

export const ADMIN_URL = 'api/admin';

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

  cancelReservation(id: number, reason?: string) {
    let params: HttpParams = new HttpParams();
    if(reason) {
      params = params.set('reason', reason);
    }
    return this.httpClient.patch(`${ADMIN_URL}/${id}/cancel`, null, {params: params});
  }
}
