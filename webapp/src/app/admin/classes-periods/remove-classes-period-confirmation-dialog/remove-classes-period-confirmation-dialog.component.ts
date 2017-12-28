import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  templateUrl: './remove-classes-period-confirmation-dialog.component.html',
})
export class RemoveClassesPeriodConfirmationDialogComponent implements OnInit {

  dateFrom: string;
  dateTo: string;

  constructor(public dialogRef: MatDialogRef<RemoveClassesPeriodConfirmationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.dateFrom = this.data.dateFrom;
    this.dateTo = this.data.dateTo;
  }

  dismiss(): void {
    this.dialogRef.close(false);
  }

  submit(): void {
    this.dialogRef.close(true);
  }

}
