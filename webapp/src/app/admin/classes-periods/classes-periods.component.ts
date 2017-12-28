import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MatSnackBar, MatSnackBarConfig, MatTableDataSource } from '@angular/material';
import { AdminService } from '../admin.service';
import { ClassesPeriod } from './classes-period';
import { RemoveClassesPeriodConfirmationDialogComponent } from './remove-classes-period-confirmation-dialog/remove-classes-period-confirmation-dialog.component';
import { CreateClassesPeriodDialogComponent } from './create-classes-period-dialog/create-classes-period-dialog.component';
import { CreateClassesPeriodDialogResult } from './create-classes-period-dialog/create-classes-period-dialog-result';

@Component({
  selector: 'hr-classes-periods',
  templateUrl: './classes-periods.component.html'
})
export class ClassesPeriodsComponent implements OnInit {

  displayedColumns = ['dateFrom', 'dateTo', 'actions'];
  dataSource = new MatTableDataSource<ClassesPeriod>([]);

  constructor(private dialog: MatDialog, private adminService: AdminService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.getClassesPeriods();
  }

  getClassesPeriods() {
    this.adminService.getClassesPeriods().subscribe(periods => {
      this.dataSource.data = periods;
    });
  }

  createClassesPeriod() {
    let dialogRef = this.dialog.open(CreateClassesPeriodDialogComponent);

    dialogRef.afterClosed().subscribe((result: CreateClassesPeriodDialogResult) => {
      if (result) {
        this.adminService.createClassesPeriod(result)
          .subscribe(() => {
            this.getClassesPeriods();
            this.showSnack('Pomyślnie utworzono okres zajęć');
          });
      }
    });
  }

  remove(classesPeriod: ClassesPeriod) {
    let conf: MatDialogConfig = new MatDialogConfig();
    conf.data = {
      dateFrom: classesPeriod.dateFrom,
      dateTo: classesPeriod.dateTo
    };

    conf.width = '300px';

    let dialogRef = this.dialog.open(RemoveClassesPeriodConfirmationDialogComponent, conf);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.adminService.removeClassPeriod(classesPeriod.id).subscribe(() => {
          this.getClassesPeriods();
          this.showSnack('Pomyślnie usunięto okres zajęc');
        });
      }
    });
  }

  private showSnack(message: string) {
    let snackConfig: MatSnackBarConfig = new MatSnackBarConfig();
    snackConfig.duration = 2000;

    this.snackBar.open(message, null, snackConfig);
  }

}
