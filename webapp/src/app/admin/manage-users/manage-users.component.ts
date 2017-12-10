import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {
  MatDialog, MatDialogConfig, MatSnackBar, MatSnackBarConfig, MatSort, MatTableDataSource, PageEvent,
  Sort
} from '@angular/material';
import { SearchUsersParams } from './search-users-params';
import { Page } from '../../core/model/page';
import { UserDetails } from './user-details';
import { AdminService } from '../admin.service';
import { LessonDateTimeService } from '../../core/service/lesson-date-time.service';
import { ChangeExpirationDateDialogComponent } from './dialogs/change-expiration-date-dialog/change-expiration-date-dialog.component';
import { RemoveUserConfirmationDialogComponent } from './dialogs/remove-user-confirmation-dialog/remove-user-confirmation-dialog.component';
import { ManageRolesDialogComponent } from './dialogs/manage-roles-dialog/manage-roles-dialog.component';

@Component({
  selector: 'hr-manage-users',
  templateUrl: './manage-users.component.html'
})
export class ManageUsersComponent implements AfterViewInit, OnInit {

  displayedColumns = ['lastName', 'email', 'enabled', 'expirationDate', 'actions'];
  page: Page<UserDetails>;
  dataSource = new MatTableDataSource<UserDetails>([]);
  actualSearchParams: SearchUsersParams;
  private todayDate: Date;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private dialog: MatDialog, private adminService: AdminService,
              private snackBar: MatSnackBar, private lessonDateTimeService: LessonDateTimeService) {
  }

  ngOnInit() {
    this.todayDate = new Date();
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

  showReservations(userDetails: UserDetails) {
    console.log(userDetails);
  }

  manageRoles(userDetails: UserDetails) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {
      oldRoles: userDetails.roles
    };

    conf.width = '300px';

    let dialogRef = this.dialog.open(ManageRolesDialogComponent, conf);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.adminService.updateRoles(result, userDetails.id)
        .subscribe(() => {
          this.showSnack('Pomyślnie zaktualizowano role użytkownika');
          this.onSearchParamsChanged(this.actualSearchParams);
        });
      }
    });
  }


  enable(userDetails: UserDetails) {
    this.adminService.enable(userDetails.id).subscribe(() => {
      this.showSnack('PomyshowSnacke aktywowano użytkownika');
      this.onSearchParamsChanged(this.actualSearchParams);
    });
  }

  disable(userDetails: UserDetails) {
    this.adminService.disable(userDetails.id).subscribe(() => {
      this.showSnack('Pomyślnie dezaktywowano użytkownika');
      this.onSearchParamsChanged(this.actualSearchParams);
    });
  }

  changeExpirationDate(userDetails: UserDetails) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {
      oldDate: userDetails.expirationDate
    };

    conf.width = '300px';

    let dialogRef = this.dialog.open(ChangeExpirationDateDialogComponent, conf);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.adminService.setExpirationDate(this.lessonDateTimeService.getDateAsString(result), userDetails.id)
          .subscribe(() => {
          this.showSnack('Pomyślnie zmieniono datę ważności konta');
          this.onSearchParamsChanged(this.actualSearchParams);
        });
      }
    });
  }

  remove(userDetails: UserDetails) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {
      name: userDetails.firstName + ' ' + userDetails.lastName
    };

    conf.width = '300px';

    let dialogRef = this.dialog.open(RemoveUserConfirmationDialogComponent, conf);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.adminService.remove(userDetails.id).subscribe(() => {
          this.onSearchParamsChanged(this.actualSearchParams);
          this.showSnack('Pomyślnie usunięto użytkownika');
        });
      }
    });
  }

  isBeforeToday(date: string) {
    let givenDate = new Date(date);
    return givenDate <= this.todayDate;
  }

  private showSnack(message: string) {
    let snackConfig: MatSnackBarConfig = new MatSnackBarConfig();
    snackConfig.duration = 2000;

    this.snackBar.open(message, null, snackConfig);
    this.onSearchParamsChanged(this.actualSearchParams);
  }

}
