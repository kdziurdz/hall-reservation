import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../core/auth/auth.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { USERS_URL } from '../../../admin/admin.service';
import { UserDetails } from '../../../admin/manage-users/user-details';

@Component({
  selector: 'hr-my-account-form',
  templateUrl: 'my-account-form.component.html',
  styleUrls: ['./my-account-form.component.scss']
})
export class MyAccountFormComponent implements OnInit{

  myAccountForm: FormGroup;
  ifChangePassword: FormControl;

  constructor(private authService: AuthService, private router: Router, private httpClient: HttpClient) {
  }

  ngOnInit(): void {

    this.myAccountForm = new FormGroup({
      login: new FormControl(null, Validators.required),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      oldPassword: new FormControl(null, Validators.required),
      newPassword: new FormControl({value: null, disabled: true}, Validators.required),
      newPasswordConfirm: new FormControl({value: null, disabled: true}, Validators.required)
    }, this.MatchPassword);

    this.ifChangePassword = new FormControl();
    this.ifChangePassword.valueChanges.subscribe(newVal => {
      if(newVal) {
        this.myAccountForm.get('newPassword').enable();
        this.myAccountForm.get('newPasswordConfirm').enable();
      } else {
        this.myAccountForm.get('newPassword').disable();
        this.myAccountForm.get('newPasswordConfirm').disable();
      }
    });

    this.httpClient.get<UserDetails>(`${USERS_URL}/actual`).subscribe((user: UserDetails) => {
      this.myAccountForm.get('firstName').setValue(user.firstName);
      this.myAccountForm.get('login').setValue(user.username);
      this.myAccountForm.get('lastName').setValue(user.lastName);
      this.myAccountForm.get('email').setValue(user.email);
    });
  }

  submitChanges(): void {
    let values = this.myAccountForm.getRawValue();
    delete values['newPasswordConfirm'];
    this.httpClient.post(`${USERS_URL}/account-update`, values).subscribe(() => {
      this.router.navigate(['/']);
    }, ()=> {
      this.myAccountForm.get('oldPassword').reset();
      // TODO toaster
    });
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
