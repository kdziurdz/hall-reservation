import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'reservation-cancellation-dialog',
  templateUrl: './reservation-cancellation-dialog.component.html',
})
export class ReservationCancellationDialog implements OnInit {

  date: string;
  hours: string;
  hall: string;

  constructor(
    public dialogRef: MatDialogRef<ReservationCancellationDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }


  ngOnInit() {
    this.date = this.data.date;
    this.hours = this.data.hours;
    this.hall = this.data.hallName;
  }

  dismiss(): void {
    this.dialogRef.close(false);
  }

  submit(): void {
    this.dialogRef.close(true);
  }

}
