import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'hr-expiration-date-dialog-dialog',
  templateUrl: './manage-roles-dialog.component.html',
})
export class ManageRolesDialogComponent implements OnInit {

  oldRoles: string[];
  newRoles: FormArray;

  constructor(public dialogRef: MatDialogRef<ManageRolesDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.oldRoles = this.data.oldRoles;
    this.newRoles = new FormArray([], Validators.required);
  }

  dismiss(): void {
    this.dialogRef.close();
  }

  submit(): void {
    this.dialogRef.close(this.newDate.value);
  }

}
