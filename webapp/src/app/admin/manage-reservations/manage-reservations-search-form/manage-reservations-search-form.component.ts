import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { Hall } from '../../../reservation/model/hall';
import { User } from '../../../core/model/user';
import { ReservationStatus } from '../../../reservation/my-reservations/reservation-status.enum';
import { ReservationService } from '../../../core/service/reservation.service';
import { AdminService } from '../../admin.service';
import { ManageReservationsSearchParams } from './manage-reservations-search-params';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'hr-manage-reservations-search-form',
  templateUrl: './manage-reservations-search-form.component.html'
})
export class ManageReservationsSearchFormComponent implements OnInit {
  searchFormGroup: FormGroup;
  hallSearchQuery: FormControl;
  usersSearchQuery: FormControl;
  querriedHalls: Observable<Array<Hall>>;
  querriedUsers: Observable<Array<User>>;
  allHalls: FormControl;
  allUsers: FormControl;
  reservationStatuses = ReservationStatus;

  @Output() onSearchParamsChanged: EventEmitter<ManageReservationsSearchParams> = new EventEmitter<ManageReservationsSearchParams>();

  constructor(private reservationService: ReservationService, private adminService: AdminService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    let queryUserId = this.route.snapshot.queryParamMap.get('id');
    let queryUserFirstName = this.route.snapshot.queryParamMap.get('firstName');
    let queryUserLastName = this.route.snapshot.queryParamMap.get('lastName');
    let userGiven: boolean = !!queryUserFirstName && !!queryUserLastName && !!queryUserId;

    this.allHalls = new FormControl(true);
    this.allUsers = new FormControl(!userGiven);
    this.hallSearchQuery = new FormControl({value: null, disabled: this.allHalls.value});
    this.usersSearchQuery = new FormControl({value: null, disabled: this.allUsers.value});


    this.searchFormGroup = new FormGroup({
      dateFrom: new FormControl(null, Validators.required),
      dateTo: new FormControl(null, Validators.required),
      hallIds: new FormArray([], Validators.required),
      userIds: new FormArray([], Validators.required),
      status: new FormControl(null, Validators.required)
    });

    if(userGiven) {
      (this.searchFormGroup.get('userIds') as FormArray)
        .push(new FormControl({firstName: queryUserFirstName, lastName: queryUserLastName, id: queryUserId}));
    }

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

  addEntityToFormArray(event: MatAutocompleteSelectedEvent, array: FormArray) {
    console.log('dodaje do', array);
    console.log('wartosc', event.option.value);
    array.push(new FormControl(event.option.value));
    console.log('dodalem');
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
    return value ? value.firstName + ' ' + value.lastName : null;
  };

  removeEntityFormArray(entity, formArray: FormArray) {
    formArray.removeAt(formArray.controls.indexOf(entity));
  }

  submit() {
    let values = this.searchFormGroup.getRawValue();

    let searchParams: ManageReservationsSearchParams = {
      dateFrom: this.formatDate(values.dateFrom),
      dateTo: this.formatDate(values.dateTo),
      hallIds: values.hallIds && values.hallIds.length > 0 ? values.hallIds.map(hall => hall.id) : null,
      userIds: values.userIds && values.userIds.length > 0 ? values.userIds.map(user => user.id) : null,
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
