import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';
import { JwtHelper } from 'angular2-jwt';
import { AuthService } from '../auth.service';
import { TokenDecoded } from './user';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient, private authService: AuthService) { //
  }

  hasRole(roleName: string): boolean {
    return this.getRoles().indexOf(roleName) > -1;
  }

  hasAnyRole(roles: string []) {
    const found: string = this.getRoles().find((role) => {
      return roles.indexOf(role) >= 0;
    });

    return !!found;
  }

  hasAllRoles(roles: string []) {
    let found: boolean = true;

    for (const role of roles) {
      if (this.getRoles().indexOf(role) === -1) {
        found = false;
        break;
      }
    }

    return found;
  }

  private getRoles(): string[] {
    if (!this.authService.isAuthenticated()) {
      return []
    }
    return this.authService.getDecodedToken().roles;
  }

}
