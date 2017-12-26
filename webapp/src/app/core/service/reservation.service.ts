import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hall } from '../../reservation/model/hall';
import { AvailableReservation } from '../../reservation/model/available-reservation';
import { AvailableReservationSlotSearchParams } from '../../reservation/reservation-creator/search-form/available-reservation-slot-search-params';
import { SaveReservation } from '../../reservation/model/save-reservation';
import { Page } from '../model/page';
import { PlannedReservation } from '../model/planned-reservations';
import { PlannedReservationSearchParams } from '../model/planned-reservation-search-params';
import { ManageReservationsSearchParams } from '../../admin/manage-reservations/manage-reservations-search-form/manage-reservations-search-params';

export const RESERVATION_URL = 'api/reservation';

@Injectable()
export class ReservationService {

  constructor(private httpClient: HttpClient) {

  }

  searchAvailableReservationSlots(searchParams: AvailableReservationSlotSearchParams): Observable<Array<AvailableReservation>> {
    let params: HttpParams = new HttpParams();

    params = params.set('dateFrom', searchParams.dateFrom);
    params = params.set('dateTo', searchParams.dateTo);
    if (searchParams.hallIds) {
      params = params.set('hallIds', searchParams.hallIds.toString());
    }
    params = params.set('duration', searchParams.duration.toString());
    return this.httpClient.get<Array<AvailableReservation>>(`${RESERVATION_URL}/search`, {params: params});
  }

  searchPlannedReservations(searchParams: PlannedReservationSearchParams): Observable<Page<PlannedReservation>> {
    let params: HttpParams = new HttpParams();

    params = params.set('dateFrom', searchParams.dateFrom);
    params = params.set('dateTo', searchParams.dateTo);
    if (searchParams.hallIds) {
      params = params.set('hallIds', searchParams.hallIds.toString());
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
    return this.httpClient.get<Page<PlannedReservation>>(`${RESERVATION_URL}`, {params: params});
  }

  searchPlannedReservationsByUsers(searchParams: ManageReservationsSearchParams): Observable<Page<PlannedReservation>> {
    let params: HttpParams = new HttpParams();

    console.log('przed request', searchParams);

    params = params.set('dateFrom', searchParams.dateFrom);
    params = params.set('dateTo', searchParams.dateTo);
    if (searchParams.hallIds) {
      params = params.set('hallIds', searchParams.hallIds.toString());
    }
    if (searchParams.userIds) {
      params = params.set('userIds', searchParams.hallIds.toString());
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
    return this.httpClient.get<Page<PlannedReservation>>(`${RESERVATION_URL}/by-users`, {params: params});
  }

  searchHalls(query: any): Observable<Array<Hall>> {
    if (query) {
      return this.httpClient.get<Array<Hall>>(`/api/hall/search?query=${query}`);
    } else {
      return Observable.of([]);
    }
  }

  reserve(reservation: SaveReservation): Observable<any> {
    return this.httpClient.post(RESERVATION_URL, reservation);
  }

  cancelReservation(id: number, reason?: string) {
    let params: HttpParams = new HttpParams();
    if(reason) {
      params = params.set('reason', reason);
    }
    return this.httpClient.patch<Array<Hall>>(`${RESERVATION_URL}/${id}/cancel`, null, {params: params});
  }
}
