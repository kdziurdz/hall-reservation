import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { User } from '../../../core/model/user';

@Component({
  selector: 'cancellation-info-dialog',
  templateUrl: './cancellation-info-dialog.component.html',
})
export class CancellationInfoDialog implements OnInit {

  date: string;
  hours: string;
  hall: string;
  reason: string;
  canceller: User;

  constructor(
    public dialogRef: MatDialogRef<CancellationInfoDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }


  ngOnInit() {
    this.date = this.data.date;
    this.hours = this.data.hours;
    this.hall = this.data.hall;
    this.reason = this.data.reason;
    this.canceller = this.data.canceller;
  }

  dismiss(): void {
    this.dialogRef.close();
  }

}
