import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { CancellationCreds } from './cancellation-creds';

@Component({
  selector: 'reservation-cancellation-dialog',
  templateUrl: './reservation-cancellation-dialog.component.html',
})
export class ReservationCancellationDialog implements OnInit {

  date: string;
  hours: string;
  hall: string;
  reason: string;

  constructor(
    public dialogRef: MatDialogRef<ReservationCancellationDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }


  ngOnInit() {
    this.date = this.data.date;
    this.hours = this.data.hours;
    this.hall = this.data.hall;
  }

  dismiss(): void {
    let result: CancellationCreds = {
      resolved: false,
    };
    this.dialogRef.close(result);
  }

  submit(): void {
    let result: CancellationCreds = {
      resolved: true,
      reason: this.reason
    };
    this.dialogRef.close(result);
  }

}
