import { Injectable, Injector } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse, HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './auth.service';
import 'rxjs/add/operator/do';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private authService: AuthService;
  private snackBar: MatSnackBar;

  constructor(
    private injector: Injector
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    this.authService = this.injector.get(AuthService);
    this.snackBar = this.injector.get(MatSnackBar);

    let token = this.authService.getToken();
    if(token) {
      request = request.clone({
        setHeaders: {
          Authorization: `${this.authService.getToken()}`
        }
      });
    }

    return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {

      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          this.showSnack("Brak autentykacji");
          this.authService.logout();
        } else if (err.status === 403) {
          this.showSnack("Brak uprawnień. Zaloguj się");
          this.authService.logout();
        } else {
          this.showSnack("Błąd serwera");
        }
      }
    });
  }

  private showSnack(message: string) {
    let snackConfig: MatSnackBarConfig = new MatSnackBarConfig();
    snackConfig.duration = 2000;

    this.snackBar.open(message, null, snackConfig);
  }
}
