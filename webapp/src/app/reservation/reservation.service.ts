import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hall } from './model/hall';
import { AvailableReservation } from './model/search-result';
import { SearchParams } from './reservation-creator/search-form/search-params';

export const RESERVATION_URL = 'api/reservation/search';

@Injectable()
export class ReservationService {

  constructor(private httpClient: HttpClient) {

  }

  searchReservations(searchParams: SearchParams): Observable<Array<AvailableReservation>> {
    let params: HttpParams = new HttpParams();

    params = params.set('dateFrom', searchParams.dateFrom);
    params = params.set('dateTo', searchParams.dateTo);
    if(searchParams.hallIds){
      params = params.set('hallIds', searchParams.hallIds.toString());
    }
    params = params.set('duration', searchParams.duration.toString());
    return this.httpClient.get<Array<AvailableReservation>>(RESERVATION_URL, {params: params})
  }

  searchHalls(query: any): Observable<Array<Hall>> {
    if(query){
      return this.httpClient.get<Array<Hall>>(`/api/hall/search?query=${query}`)
    } else {
      return Observable.of([])
    }
  }
}
