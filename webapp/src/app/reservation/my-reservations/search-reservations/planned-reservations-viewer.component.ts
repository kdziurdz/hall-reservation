import { AfterViewInit, Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog, MatSort, MatSortable, MatTableDataSource, PageEvent, Sort } from '@angular/material';
import { PlannedReservation } from '../planned-reservations';
import { ReservationService } from '../../reservation.service';
import { Page } from '../../../core/model/page';
import { PlannedReservationSearchParams } from './planned-reservation-search-form/planned-reservation-search-params';

@Component({
  selector: 'hr-planned-reservations-viewer',
  templateUrl: './planned-reservations-viewer.component.html'
})
export class PlannedReservationsViewerComponent implements OnChanges, AfterViewInit {

  @ViewChild(MatSort) sort: MatSort;

  displayedColumns = ['date', 'lessonNumbers', 'hall'];
  page: Page<PlannedReservation>;
  dataSource = new MatTableDataSource<PlannedReservation>([]);
  actualSearchParams: PlannedReservationSearchParams;

  constructor(private dialog: MatDialog, private reservationService: ReservationService) {
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
    console.log(pageEvent);
  }

  onSortChange(sort: Sort){
    this.actualSearchParams.sort =sort.active+','+sort.direction
    this.onSearchParamsChanged(this.actualSearchParams);
  }

  onSearchParamsChanged(params: PlannedReservationSearchParams) {
    this.actualSearchParams = params;
    this.reservationService.searchPlannedReservations(params).subscribe(searchResults => {
      this.page = searchResults;
      this.dataSource.data = searchResults.content;
    });
  }
}
