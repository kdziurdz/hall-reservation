import { MatDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { CreateUserDialogCreds } from './create-user-dialog-result';

@Component({
  selector: 'hr-create-user-dialog',
  templateUrl: './create-user-dialog.component.html',
})
export class CreateUserDialogComponent implements OnInit {

  createUserFormGroup: FormGroup;
  todayDate: Date;

  constructor(public dialogRef: MatDialogRef<CreateUserDialogComponent>) {
  }

  ngOnInit() {
    this.todayDate = new Date();
    this.createUserFormGroup = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
      confirmPassword: new FormControl(null, Validators.required),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      roles: new FormArray([], Validators.required),
      expirationDate: new FormControl(null, Validators.required),
      enabled: new FormControl(true),
      email: new FormControl(null, Validators.email),
    }, this.MatchPassword);
  }

  dismiss(): void {
    this.dialogRef.close();
  }
  submit(): void {
    let values: CreateUserDialogCreds = this.createUserFormGroup.getRawValue();
    console.log('take dane', values);
    values.expirationDate = this.formatDate(values.expirationDate as Date);
    this.dialogRef.close(values);
  }

  private MatchPassword(AC: AbstractControl) {
    let password = AC.get('password').value;
    let repeatPassword = AC.get('confirmPassword').value;
    if(password != repeatPassword) {
      AC.get('confirmPassword').setErrors( {MatchPassword: true} )
    } else {
      return null
    }
  }

  private formatDate(d: Date): string {
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    let year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }

}
