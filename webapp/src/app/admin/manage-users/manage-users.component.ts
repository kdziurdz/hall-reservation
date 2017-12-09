import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatDialog, MatSnackBar, MatSort, MatTableDataSource, PageEvent, Sort } from '@angular/material';
import { SearchUsersParams } from './search-users-params';
import { Page } from '../../core/model/page';
import { UserDetails } from './user-details';
import { AdminService } from '../admin.service';

@Component({
  selector: 'hr-manage-users',
  templateUrl: './manage-users.component.html'
})
export class ManageUsersComponent implements AfterViewInit {

  displayedColumns = ['lastName', 'email', 'enabled', 'expirationDate', 'actions'];
  page: Page<UserDetails>;
  dataSource = new MatTableDataSource<UserDetails>([]);
  actualSearchParams: SearchUsersParams;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private dialog: MatDialog, private adminService: AdminService, private snackBar: MatSnackBar) {
  }


  ngAfterViewInit() {
    this.dataSource.sort = this.sort;

    let inTwoWeeks = new Date();
    inTwoWeeks.setDate(inTwoWeeks.getDate() + 14);


    let params: SearchUsersParams = {
      sort: 'lastName,asc',
      pageSize: 10,
      pageNumber: 0,
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

  onSearchParamsChanged(params: SearchUsersParams) {
    this.actualSearchParams = params;
    this.adminService.searchUsers(params).subscribe(searchResults => {
      this.page = searchResults;
      this.dataSource.data = searchResults.content;
    });
  }

}
