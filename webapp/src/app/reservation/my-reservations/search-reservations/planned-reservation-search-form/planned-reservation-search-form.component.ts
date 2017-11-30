import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { PlannedReservationSearchParams } from './planned-reservation-search-params';
import { Hall } from '../../../model/hall';
import { ReservationStatus } from '../../reservation-status.enum';
import { ReservationService } from '../../../reservation.service';

@Component({
  selector: 'hr-planned-reservation-search-form',
  templateUrl: './planned-reservation-search-form.component.html',
  styleUrls: ['./planned-reservation-search-form.component.scss']
})
export class PlannedReservationSearchFormComponent implements OnInit {
  searchFormGroup: FormGroup;
  hallSearchQuery: FormControl;
  allHalls: FormControl;
  querriedHalls: Observable<Array<Hall>>;
  reservationStatuses = ReservationStatus;

  @Output() onSearchParamsChanged: EventEmitter<PlannedReservationSearchParams> = new EventEmitter<PlannedReservationSearchParams>();

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.allHalls = new FormControl(true);
    this.hallSearchQuery = new FormControl({value: null, disabled: this.allHalls.value});


    this.searchFormGroup = new FormGroup({
      dateFrom: new FormControl(null, Validators.required),
      dateTo: new FormControl(null, Validators.required),
      hallIds: new FormArray([], Validators.required),
      status: new FormControl(null, Validators.required)
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

    let searchParams: PlannedReservationSearchParams = {
      dateFrom: this.formatDate(values.dateFrom),
      dateTo: this.formatDate(values.dateTo),
      hallIds: this.searchFormGroup.get('hallIds').disabled ? null : values.hallIds.map(hall => hall.id),
      status: values.status
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
