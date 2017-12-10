import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, Validators } from '@angular/forms';
import { Roles } from '../../roles.const';

@Component({
  selector: 'hr-expiration-date-dialog-dialog',
  templateUrl: './manage-roles-dialog.component.html',
})
export class ManageRolesDialogComponent implements OnInit {
  newRoles: FormArray;
  possibleRoles: string[] = Roles;

  constructor(public dialogRef: MatDialogRef<ManageRolesDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    console.log('possibleRoles', this.possibleRoles);
    //console.log('this.data', this.data);
    this.newRoles = new FormArray(this.data.oldRoles.map(oldRole => new FormControl(oldRole)), Validators.required);
    console.log(this.newRoles.controls)
}

  dismiss(): void {
    this.dialogRef.close();
  }

  submit(): void {
    this.dialogRef.close(this.newRoles.getRawValue());
  }

  removeRole(role: FormControl): void {
    console.log('usuwam role', role);
    console.log('this.newRoles.controls.indexOf(role)', this.newRoles.controls.indexOf(role));

    this.newRoles.removeAt(this.newRoles.controls.indexOf(role));
  }

}
