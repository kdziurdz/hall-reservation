import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import {
  MatDialog, MatDialogConfig, MatSort, MatSortable, MatTableDataSource, PageEvent,
  Sort
} from '@angular/material';
import { PlannedReservation } from '../planned-reservations';
import { ReservationService } from '../../reservation.service';
import { Page } from '../../../core/model/page';
import { PlannedReservationSearchParams } from './planned-reservation-search-form/planned-reservation-search-params';
import { ReservationCancellationDialog } from './dialogs/reservation-cancellation-dialog/reservation-confirmation-dialog.component';
import { LessonDateTimeService } from '../../../core/service/lesson-date-time.service';

@Component({
  selector: 'hr-planned-reservations-viewer',
  templateUrl: './planned-reservations-viewer.component.html'
})
export class PlannedReservationsViewerComponent implements OnChanges, AfterViewInit, OnInit {

  @ViewChild(MatSort) sort: MatSort;

  displayedColumns = ['date', 'lessonNumbers', 'hall', 'actions'];
  page: Page<PlannedReservation>;
  dataSource = new MatTableDataSource<PlannedReservation>([]);
  actualSearchParams: PlannedReservationSearchParams;
  todayDate: Date;

  constructor(private dialog: MatDialog, private reservationService: ReservationService,
              private lessonDateTimeservice: LessonDateTimeService) {
  }

  ngOnInit() {
    this.todayDate = new Date();
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes.searchResults && changes.searchResults.currentValue){
      this.dataSource.data = changes.searchResults.currentValue.content;
    }
  }

  onPageChange(pageEvent: PageEvent){
    this.actualSearchParams.pageSize = pageEvent.pageSize;
    this.actualSearchParams.pageNumber = pageEvent.pageIndex;
    this.onSearchParamsChanged(this.actualSearchParams);
  }

  onSortChange(sort: Sort){
    this.actualSearchParams.sort =sort.active+','+sort.direction;
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
    conf.data = {date: element.date, hours: this.lessonDateTimeservice.getReservationTimeAsString(element.lessonNumbers),
                  hall: element.hall.name };
    conf.width = '300px';


    let dialogRef = this.dialog.open(ReservationCancellationDialog, conf);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.reservationService.cancelReservation(element.id).subscribe(() => console.log('NOTYYFIKACJA'));
      }
    });
  }
  info(element: PlannedReservation) {
    console.log(element);
  }

  isBeforeToday(date: string){
    let givenDate = new Date(date);
    return givenDate <= this.todayDate;
  }
}
