import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';


const LOGIN_URL = 'api/login';
const LOGIN_ROUTE = 'login';
const TOKEN = 'token';
const AUTHORIZATION_HEADER = 'Authorization';

@Injectable()
export class AuthService {

  constructor(private httpClient: HttpClient, private router: Router) { //
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  public isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token
  }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.post(LOGIN_URL, {username, password}, { observe: 'response' })
    .do((response: any) => this.saveToken(response.headers.get(AUTHORIZATION_HEADER)));
   }

  logout(): void {
    this.router.navigate([LOGIN_ROUTE]);
    localStorage.removeItem(TOKEN);
  }

  private saveToken(token: string): void {
    return localStorage.setItem(TOKEN, token);
  }

}
