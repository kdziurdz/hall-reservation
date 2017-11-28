import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'reservation-confirmation-dialog',
  templateUrl: './reservation-confirmation-dialog.component.html',
})
export class ReservationConfirmationDialog implements OnInit {

  date: string;
  hours: string;

  constructor(
    public dialogRef: MatDialogRef<ReservationConfirmationDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }


  ngOnInit() {
    this.date = this.data.date;
    this.hours = this.data.hours;
  }

  dismiss(): void {
    this.dialogRef.close(false);
  }

  submit(): void {
    this.dialogRef.close(true);
  }

}
