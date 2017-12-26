import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {
  MatDialog, MatDialogConfig, MatSnackBar, MatSnackBarConfig, MatSort, MatTableDataSource, PageEvent,
  Sort
} from '@angular/material';
import { PlannedReservation } from '../../core/model/planned-reservations';
import { Page } from '../../core/model/page';
import { ReservationService } from '../../core/service/reservation.service';
import { LessonDateTimeService } from '../../core/service/lesson-date-time.service';
import { ReservationCancellationDialog } from '../../shared/components/reservation-cancellation-dialog/reservation-cancellation-dialog.component';
import { CancellationInfoDialog } from '../../shared/components/cancellation-info-dialog/cancellation-info-dialog.component';
import { ManageReservationsSearchParams } from './manage-reservations-search-form/manage-reservations-search-params';

@Component({
  selector: 'hr-manage-reservations',
  templateUrl: './manage-reservations.component.html'
})
export class ManageReservationsComponent implements OnInit, AfterViewInit {

  displayedColumns = ['date', 'lessonNumbers', 'hall', 'owner', 'actions'];
  page: Page<PlannedReservation>;
  dataSource = new MatTableDataSource<PlannedReservation>([]);
  actualSearchParams: ManageReservationsSearchParams;
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

  onSearchParamsChanged(params: ManageReservationsSearchParams) {
    this.actualSearchParams = params;
    this.reservationService.searchPlannedReservationsByUsers(params).subscribe(searchResults => {
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

          this.snackBar.open('Pomyślnie odwołano rezerwację', null, snackConfig);
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
