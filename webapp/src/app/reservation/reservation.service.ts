import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hall } from './model/hall';
import { AvailableReservation } from './model/available-reservation';
import { SearchParams } from './reservation-creator/search-form/search-params';
import { SaveReservation } from './model/save-reservation';

export const RESERVATION_URL = 'api/reservation';

@Injectable()
export class ReservationService {

  constructor(private httpClient: HttpClient) {

  }

  searchReservations(searchParams: SearchParams): Observable<Array<AvailableReservation>> {
    let params: HttpParams = new HttpParams();

    params = params.set('dateFrom', searchParams.dateFrom);
    params = params.set('dateTo', searchParams.dateTo);
    if (searchParams.hallIds) {
      params = params.set('hallIds', searchParams.hallIds.toString());
    }
    params = params.set('duration', searchParams.duration.toString());
    return this.httpClient.get<Array<AvailableReservation>>(`${RESERVATION_URL}/search`, {params: params});
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
}
