import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { PlannedReservationSearchParams } from '../../../../core/model/planned-reservation-search-params';
import { Hall } from '../../../../reservation/model/hall';
import { ReservationStatus } from '../../../../reservation/my-reservations/reservation-status.enum';
import { ReservationService } from '../../../../core/service/reservation.service';
import { User } from '../../../../core/model/user';
import { AdminService } from '../../../admin.service';

@Component({
  selector: 'hr-reservations-by-halls-search-form',
  templateUrl: './reservations-by-halls-search-form.component.html'
})
export class ReservationsByHallsSearchFormComponent implements OnInit {
  searchFormGroup: FormGroup;
  hallSearchQuery: FormControl;
  usersSearchQuery: FormControl;
  querriedHalls: Observable<Array<Hall>>;
  querriedUsers: Observable<Array<User>>;
  allHalls: FormControl;
  allUsers: FormControl;
  reservationStatuses = ReservationStatus;

  @Output() onSearchParamsChanged: EventEmitter<PlannedReservationSearchParams> = new EventEmitter<PlannedReservationSearchParams>();

  constructor(private reservationService: ReservationService, private adminService: AdminService) {
  }

  ngOnInit(): void {
    this.allHalls = new FormControl(true);
    this.allUsers = new FormControl(true);
    this.hallSearchQuery = new FormControl({value: null, disabled: this.allHalls.value});
    this.usersSearchQuery = new FormControl({value: null, disabled: this.allUsers.value});


    this.searchFormGroup = new FormGroup({
      dateFrom: new FormControl(null, Validators.required),
      dateTo: new FormControl(null, Validators.required),
      hallIds: new FormArray([], Validators.required),
      userIds: new FormArray([], Validators.required),
      status: new FormControl(null, Validators.required)
    });

    if (this.allHalls.value) {
      this.searchFormGroup.get('hallIds').disable();
    }

    if (this.allUsers.value) {
      this.searchFormGroup.get('userIds').disable();
    }

    this.hallSearchQuery.valueChanges.subscribe(value => {
      this.querriedHalls = this.searchHall(value);
    });

    this.usersSearchQuery.valueChanges.subscribe(value => {
      this.querriedUsers = this.searchUser(value);
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

    this.allUsers.valueChanges.subscribe(value => {
      if (value) {
        this.usersSearchQuery.disable();
        this.searchFormGroup.get('userIds').disable();
      } else {
        this.usersSearchQuery.enable();
        this.searchFormGroup.get('userIds').enable();
      }

    });
  }

  hallSelected(event: MatAutocompleteSelectedEvent) {
    this.searchFormGroup.get('hallIds').setValue(event.option.value);
  }

  userSelected(event: MatAutocompleteSelectedEvent) {
    this.searchFormGroup.get('userIds').setValue(event.option.value);
  }

  searchHall(query): Observable<Array<Hall>> {
    return this.reservationService.searchHalls(query);
  }

  searchUser(query): Observable<Array<User>> {
    return this.adminService.querySearchUsers(query);
  }

  hallSearchDisplayTransform = (value: any) => {
    return value ? value.name : null;
  };

  userSearchDisplayTransform = (value: any) => {
    return value ? value.firstName + value.lastName: null;
  };

  removeEntityFormArray(entity, formArray: FormArray) {
    formArray.removeAt(formArray.controls.indexOf(entity));
  }

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
