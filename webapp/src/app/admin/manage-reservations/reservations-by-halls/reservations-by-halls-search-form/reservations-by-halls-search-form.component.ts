import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { PlannedReservationSearchParams } from '../../../../core/model/planned-reservation-search-params';
import { Hall } from '../../../../reservation/model/hall';
import { ReservationStatus } from '../../../../reservation/my-reservations/reservation-status.enum';
import { ReservationService } from '../../../../core/service/reservation.service';

@Component({
  selector: 'hr-reservations-by-halls-search-form',
  templateUrl: './reservations-by-halls-search-form.component.html'
})
export class ReservationsByHallsSearchFormComponent implements OnInit {
  searchFormGroup: FormGroup;
  hallSearchQuery: FormControl;
  querriedHalls: Observable<Array<Hall>>;
  reservationStatuses = ReservationStatus;

  @Output() onSearchParamsChanged: EventEmitter<PlannedReservationSearchParams> = new EventEmitter<PlannedReservationSearchParams>();

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.searchFormGroup = new FormGroup({
      dateFrom: new FormControl(null, Validators.required),
      dateTo: new FormControl(null, Validators.required),
      hallIds: new FormControl(null, Validators.required),
      status: new FormControl(null, Validators.required)
    });

    this.hallSearchQuery.valueChanges.subscribe(value => {
      this.querriedHalls = this.searchHall(value);
    });
  }

  hallSelected(event: MatAutocompleteSelectedEvent) {
    console.log('event', event);
    this.searchFormGroup.get('hallIds').setValue(event.option.value);
  }

  searchHall(query): Observable<Array<Hall>> {
    return this.reservationService.searchHalls(query);
  }

  hallSearchDisplayTransform = (value: any) => {
    return value ? value.name : null;
  };

  submit() {
    let values = this.searchFormGroup.getRawValue();

    let searchParams: PlannedReservationSearchParams = {
      dateFrom: this.formatDate(values.dateFrom),
      dateTo: this.formatDate(values.dateTo),
      hallIds: Array.of(values.hallIds),
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
