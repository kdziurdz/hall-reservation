import { ApplicationRef, Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { JwtHelper } from 'angular2-jwt';
import { TokenDecoded } from './user/user';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { FirstLoginDialogCreds } from './first-login-dialog/first-login-dialog-result';
import { USERS_URL } from '../../admin/admin.service';


const LOGIN_URL = 'api/login';
const LOGIN_ROUTE = 'login';
const TOKEN = 'token';
const AUTHORIZATION_HEADER = 'Authorization';

@Injectable()
export class AuthService {

  private isAuthenticatedSubject: BehaviorSubject<boolean>;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  isAuthenticated(): boolean {
    let jwtHelper = new JwtHelper();
    const token = this.getToken();
    if(!token){
      return false;
    }

    if(jwtHelper.isTokenExpired(token)){
      this.logout();
      return false;
    }
    return true;
  }

  isStillAuthenticated(): Observable<boolean> {
    if(!this.isAuthenticatedSubject) {
      this.isAuthenticatedSubject = new BehaviorSubject(this.isAuthenticated());
    }
    return this.isAuthenticatedSubject.asObservable();
  }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.post(LOGIN_URL, {username, password}, { observe: 'response' })
    .do((response: any) => {
      this.saveToken(response.headers.get(AUTHORIZATION_HEADER));
      this.isAuthenticatedSubject.next(true);
    });
   }

  logout(): void {
    this.router.navigate([LOGIN_ROUTE]);
    localStorage.removeItem(TOKEN);
    this.isAuthenticatedSubject.next(false);
  }

  private saveToken(token: string): void {
    return localStorage.setItem(TOKEN, token);
  }

  getDecodedToken(): TokenDecoded {
    return this.decodeToken()
  }


  private decodeToken() {
    let jwtHelper: JwtHelper = new JwtHelper();
    return jwtHelper.decodeToken(this.getToken());
  }

  createPassword(result: FirstLoginDialogCreds): Observable<any> {
    return this.httpClient.post(`${USERS_URL}/create-password`, result)
  }
}
