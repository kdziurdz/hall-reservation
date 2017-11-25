import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../core/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'hr-login-form',
  templateUrl: 'login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit{

  loginForm: FormGroup;

  constructor(private authService: AuthService, private router: Router) {
  }


  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required)
    })
  }

  submitLogin(): void {
    let values = this.loginForm.getRawValue();
    this.authService.login(values.username, values.password).subscribe(() => {
      this.router.navigate(['/']);
    }, ()=> {
      this.loginForm.reset();
      // TODO toaster
    });
  }

}
