import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'hr-change-expiration-date-dialog',
  templateUrl: './change-expiration-date-dialog.component.html',
})
export class ChangeExpirationDateDialogComponent implements OnInit {

  oldDate: string;
  newDate: FormControl;
  todayDate: Date;

  constructor(public dialogRef: MatDialogRef<ChangeExpirationDateDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.todayDate = new Date();
    this.oldDate = this.data.oldDate;
    this.newDate = new FormControl(null, Validators.required);
  }

  dismiss(): void {
    this.dialogRef.close();
  }

  submit(): void {
    this.dialogRef.close(this.newDate.value);
  }

}
