import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'hr-remove-user-confirmation-dialog',
  templateUrl: './remove-user-confirmation-dialog.component.html',
})
export class RemoveUserConfirmationDialogComponent implements OnInit {

  name: string;

  constructor(public dialogRef: MatDialogRef<RemoveUserConfirmationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.name = this.data.name;
  }

  dismiss(): void {
    this.dialogRef.close(false);
  }

  submit(): void {
    this.dialogRef.close(true);
  }

}
