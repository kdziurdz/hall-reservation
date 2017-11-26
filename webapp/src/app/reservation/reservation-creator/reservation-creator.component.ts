import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Hall } from '../model/hall';
import { Observable } from 'rxjs';

@Component({
  selector: 'hr-reservation-creator',
  templateUrl: './reservation-creator.component.html',
  styleUrls: ['./reservation-creator.component.scss']
})
export class ReservationCreatorComponent implements OnInit {

  searchFormGroup: FormGroup;
  hallSearchQuery: FormControl;
  querriedHalls: Observable<Array<Hall>>;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.hallSearchQuery = new FormControl();

    this.hallSearchQuery.valueChanges.subscribe(value => {
     this.querriedHalls = this.searhHall(value);
    });

    this.searchFormGroup = new FormGroup({
      searchQuery: new FormControl()
    })
  }

  searhHall(query): Observable<Array<Hall>> {
    if(query){
      return this.httpClient.get<Array<Hall>>(`/api/hall/search?query=${query}`)
    } else {
      return Observable.of([])
    }
  }

  submit(){
    console.log('submit', this.searchFormGroup.getRawValue());
  }
}
