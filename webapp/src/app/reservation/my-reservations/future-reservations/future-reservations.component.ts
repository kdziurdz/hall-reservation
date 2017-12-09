import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {
  MatDialog, MatDialogConfig, MatSnackBar, MatSnackBarConfig, MatSort, MatTableDataSource, PageEvent,
  Sort
} from '@angular/material';
import { PlannedReservationSearchParams } from '../../../core/model/planned-reservation-search-params';
import { PlannedReservation } from '../../../core/model/planned-reservations';
import { Page } from '../../../core/model/page';
import { ReservationService } from '../../reservation.service';
import { LessonDateTimeService } from '../../../core/service/lesson-date-time.service';
import { ReservationStatus } from '../reservation-status.enum';
import { ReservationCancellationDialog } from '../dialogs/reservation-cancellation-dialog/reservation-cancellation-dialog.component';
import { CancellationInfoDialog } from '../dialogs/cancellation-info-dialog/cancellation-info-dialog.component';

@Component({
  selector: 'hr-future-reservations',
  templateUrl: './future-reservations.component.html'
})
export class FutureReservationsComponent implements OnInit, AfterViewInit {

  displayedColumns = ['date', 'lessonNumbers', 'hall', 'actions'];
  page: Page<PlannedReservation>;
  dataSource = new MatTableDataSource<PlannedReservation>([]);
  actualSearchParams: PlannedReservationSearchParams;
  todayDate: Date;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private dialog: MatDialog, private reservationService: ReservationService,
              private lessonDateTimeService: LessonDateTimeService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.todayDate = new Date();
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;

    let inTwoWeeks = new Date();
    inTwoWeeks.setDate(inTwoWeeks.getDate() + 14);


    let params: PlannedReservationSearchParams = {
      dateFrom: this.lessonDateTimeService.getDateAsString(this.todayDate),
      dateTo: this.lessonDateTimeService.getDateAsString(inTwoWeeks),
      hallIds: null,
      sort: 'date,asc',
      pageSize: 10,
      pageNumber: 0,
      status: [ReservationStatus.ACTIVE, ReservationStatus.CANCELLED]
    };
    this.onSearchParamsChanged(params);
  }


  onPageChange(pageEvent: PageEvent) {
    this.actualSearchParams.pageSize = pageEvent.pageSize;
    this.actualSearchParams.pageNumber = pageEvent.pageIndex;
    this.onSearchParamsChanged(this.actualSearchParams);
  }

  onSortChange(sort: Sort) {
    this.actualSearchParams.sort = sort.active + ',' + sort.direction;
    this.onSearchParamsChanged(this.actualSearchParams);
  }

  onSearchParamsChanged(params: PlannedReservationSearchParams) {
    console.log('onSearchParamsChanged');
    this.actualSearchParams = params;
    this.reservationService.searchPlannedReservations(params).subscribe(searchResults => {
      console.log(searchResults);
      this.page = searchResults;
      this.dataSource.data = searchResults.content;
    });
  }

  cancel(element: PlannedReservation) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {
      date: element.date, hours: this.lessonDateTimeService.getReservationTimeAsString(element.lessonNumbers),
      hall: element.hall.name
    };
    conf.width = '300px';

    let dialogRef = this.dialog.open(ReservationCancellationDialog, conf);

    dialogRef.afterClosed().subscribe(result => {
      if (result.resolved) {
        this.reservationService.cancelReservation(element.id, result.reason).subscribe(() => {

          let snackConfig: MatSnackBarConfig = new MatSnackBarConfig();
          snackConfig.duration = 2000;

          this.snackBar.open("Pomyślnie odwołano rezerwację", null, snackConfig);
          this.onSearchParamsChanged(this.actualSearchParams);
        });
      }
    });
  }

  info(element: PlannedReservation) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {
      date: element.date, hours: this.lessonDateTimeService.getReservationTimeAsString(element.lessonNumbers),
      hall: element.hall.name, canceller: element.canceller, reason: element.cancellationReason
    };
    conf.width = '300px';
    this.dialog.open(CancellationInfoDialog, conf);
  }

  isBeforeToday(date: string) {
    let givenDate = new Date(date);
    return givenDate <= this.todayDate;
  }

}
