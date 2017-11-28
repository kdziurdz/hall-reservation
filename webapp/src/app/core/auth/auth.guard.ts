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
    let isAuthenticated = this.authService.isAuthenticated();

    if(isAuthenticated && url === LOGIN_URL) {
      this.router.navigate(['/']);
      return true;
    } else if (isAuthenticated) {
      return true;
    } else {
      this.router.navigate([LOGIN_URL]);
      return true;
    }
  }

}
