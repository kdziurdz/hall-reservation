import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { FirstLoginDialogCreds } from './first-login-dialog-result';

@Component({
  selector: 'hr-first-login-dialog',
  templateUrl: './first-login-dialog.component.html',
})
export class FirstLoginDialogComponent implements OnInit {

  firstLoginFormGroup: FormGroup;

  constructor(public dialogRef: MatDialogRef<FirstLoginDialogComponent>) {
  }

  ngOnInit() {
    this.firstLoginFormGroup = new FormGroup({
      username: new FormControl(null, Validators.required),
      oldPassword: new FormControl(null, Validators.required),
      newPassword: new FormControl(null, Validators.required),
      newPasswordConfirm: new FormControl(null, Validators.required)
    }, this.MatchPassword);
  }

  dismiss(): void {
    this.dialogRef.close();
  }

  submit(): void {
    let values: FirstLoginDialogCreds = this.firstLoginFormGroup.getRawValue();
    this.dialogRef.close(values);
  }

  private MatchPassword(AC: AbstractControl) {
    let password = AC.get('newPassword').value;
    let repeatPassword = AC.get('newPasswordConfirm').value;
    if(password != repeatPassword) {
      AC.get('newPasswordConfirm').setErrors( {MatchPassword: true} )
    } else {
      return null
    }
  }

}
