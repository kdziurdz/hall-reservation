import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {
  MatDialog, MatDialogConfig, MatSnackBar, MatSnackBarConfig, MatSort, MatTableDataSource, PageEvent,
  Sort
} from '@angular/material';
import { PlannedReservation } from '../../../core/model/planned-reservations';
import { ReservationService } from '../../reservation.service';
import { Page } from '../../../core/model/page';
import { PlannedReservationSearchParams } from '../../../core/model/planned-reservation-search-params';
import { LessonDateTimeService } from '../../../core/service/lesson-date-time.service';
import { CancellationInfoDialog } from '../dialogs/cancellation-info-dialog/cancellation-info-dialog.component';
import { ReservationCancellationDialog } from '../dialogs/reservation-cancellation-dialog/reservation-cancellation-dialog.component';

@Component({
  selector: 'hr-planned-reservations-viewer',
  templateUrl: './planned-reservations-viewer.component.html'
})
export class PlannedReservationsViewerComponent implements AfterViewInit, OnInit {

  @ViewChild(MatSort) sort: MatSort;

  displayedColumns = ['date', 'lessonNumbers', 'hall', 'actions'];
  page: Page<PlannedReservation>;
  dataSource = new MatTableDataSource<PlannedReservation>([]);
  actualSearchParams: PlannedReservationSearchParams;
  todayDate: Date;

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

  onSearchParamsChanged(params: PlannedReservationSearchParams) {
    this.actualSearchParams = params;
    this.reservationService.searchPlannedReservations(params).subscribe(searchResults => {
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
      if (result) {
        this.reservationService.cancelReservation(element.id).subscribe(() => {

          let snackConfig: MatSnackBarConfig = new MatSnackBarConfig();
          snackConfig.duration = 2000;

          this.snackBar.open("Pomyślnie odwołano rezerwację", null, snackConfig);
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
  }

  isBeforeToday(date: string) {
    let givenDate = new Date(date);
    return givenDate <= this.todayDate;
  }
}
