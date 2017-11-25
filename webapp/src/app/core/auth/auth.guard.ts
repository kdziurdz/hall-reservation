import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';

const LOGIN_URL = '/login';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let url: string = state.url;

    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    console.log('checkLogin');
    let isAuthenticated = this.authService.isAuthenticated();

    if(isAuthenticated && url === LOGIN_URL) {
      console.log('1');
      this.router.navigate(['/']);
      return true;
    } else if (isAuthenticated) {
      console.log('2');
      return true;
    } else {
      console.log('3');
      this.router.navigate([LOGIN_URL]);
      return true;
    }
  }

}
