import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';

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
      password: new FormControl(null, Validators.required),
      repeatPassword: new FormControl(null, Validators.required)
    }, this.MatchPassword);
  }

  dismiss(): void {
    this.dialogRef.close();
  }

  submit(): void {
    let values = this.firstLoginFormGroup.getRawValue();
    this.dialogRef.close(values.password);
  }

  private MatchPassword(AC: AbstractControl) {
    let password = AC.get('password').value; // to get value in input tag
    let repeatPassword = AC.get('repeatPassword').value; // to get value in input tag
    if(password != repeatPassword) {
      console.log('false');
      AC.get('repeatPassword').setErrors( {MatchPassword: true} )
    } else {
      console.log('true');
      return null
    }
  }

}
