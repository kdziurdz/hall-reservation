import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { Hall } from '../../model/hall';
import { AVAILABLE_RESERVATION_DURATION_HOURS } from '../../reservation.consts';
import { ReservationService } from '../../reservation.service';
import { SearchParams } from './search-params';

@Component({
  selector: 'hr-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent implements OnInit {
  searchFormGroup: FormGroup;
  hallSearchQuery: FormControl;
  allHalls: FormControl;
  querriedHalls: Observable<Array<Hall>>;
  availableDuration = AVAILABLE_RESERVATION_DURATION_HOURS;
  todayDate: Date;

  @Output() onSearchParamsChanged: EventEmitter<SearchParams> = new EventEmitter<SearchParams>();

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.todayDate = new Date();
    this.allHalls = new FormControl(true);
    this.hallSearchQuery = new FormControl({value: null, disabled: this.allHalls.value});


    this.searchFormGroup = new FormGroup({
      dateFrom: new FormControl(null, Validators.required),
      dateTo: new FormControl(null, Validators.required),
      duration: new FormControl(null, Validators.required),
      hallIds: new FormArray([], Validators.required)
    });

    if (this.allHalls.value) {
      this.searchFormGroup.get('hallIds').disable();
    }

    this.hallSearchQuery.valueChanges.subscribe(value => {
      this.querriedHalls = this.searchHall(value);
    });

    this.allHalls.valueChanges.subscribe(value => {
      if (value) {
        this.hallSearchQuery.disable();
        this.searchFormGroup.get('hallIds').disable();
      } else {
        this.hallSearchQuery.enable();
        this.searchFormGroup.get('hallIds').enable();
      }

    });
  }

  hallSelected(event: MatAutocompleteSelectedEvent) {
    let halls: FormArray = this.searchFormGroup.get('hallIds') as FormArray;
    if (!halls.controls.find(hall => hall.value.id === event.option.value.id)) {
      halls.push(new FormControl(event.option.value));
    }
  }

  searchHall(query): Observable<Array<Hall>> {
    return this.reservationService.searchHalls(query);
  }

  hallSearchDisplayTransform = (value: any) => {
    return value ? value.name : null;
  };

  removeHallFromArray(hall) {
    let halls: FormArray = this.searchFormGroup.get('hallIds') as FormArray;
    halls.removeAt(halls.controls.indexOf(hall));
  }

  submit() {
    let values = this.searchFormGroup.getRawValue();

    let searchParams: SearchParams = {
      dateFrom: this.formatDate(values.dateFrom),
      dateTo: this.formatDate(values.dateTo),
      hallIds: this.searchFormGroup.get('hallIds').disabled ? null : values.hallIds.map(hall => hall.id),
      duration: values.duration as string
    };

    this.onSearchParamsChanged.emit(searchParams);
  }

  private formatDate(d: Date): string {
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    let year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }
}
