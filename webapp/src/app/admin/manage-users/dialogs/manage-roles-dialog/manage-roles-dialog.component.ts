import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, Validators } from '@angular/forms';

@Component({
  templateUrl: './manage-roles-dialog.component.html',
})
export class ManageRolesDialogComponent implements OnInit {
  newRoles: FormArray;

  constructor(public dialogRef: MatDialogRef<ManageRolesDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.newRoles = new FormArray(this.data.oldRoles.map(oldRole => new FormControl(oldRole)),
      Validators.required);
  }
  
  dismiss(): void {
    this.dialogRef.close();
  }

  submit(): void {
    this.dialogRef.close(this.newRoles.getRawValue());
  }
}
