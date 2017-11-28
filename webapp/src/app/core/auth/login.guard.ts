import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';

const LOGIN_URL = '/login';

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.checkLogin();
  }

  checkLogin(): boolean {
    let isAuthenticated = this.authService.isAuthenticated();

    if(isAuthenticated) {
      this.router.navigate(['/']);
      return false;
    } else {
      return true;
    }
  }

}
