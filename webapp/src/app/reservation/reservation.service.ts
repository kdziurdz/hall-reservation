import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hall } from './model/hall';

export const RESERVATION_URL = 'api/reservation/search';

@Injectable()
export class ReservationService {

  constructor(private httpClient: HttpClient) {

  }

  searchReservations(dateFrom: string, dateTo: string, hallIds: number[], duration: any): Observable<any> {
    let params: HttpParams = new HttpParams();

    params = params.set('dateFrom', dateFrom);
    params = params.set('dateTo', dateTo);
    if(hallIds){
      params = params.set('hallIds', hallIds.toString());
    }
    params = params.set('duration', duration.toString());
    return this.httpClient.get(RESERVATION_URL, {params: params})
  }

  searchHalls(query: any): Observable<Array<Hall>> {
    if(query){
      return this.httpClient.get<Array<Hall>>(`/api/hall/search?query=${query}`)
    } else {
      return Observable.of([])
    }
  }
}
