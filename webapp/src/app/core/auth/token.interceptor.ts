import { Injectable, Injector } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './auth.service';
import 'rxjs/add/operator/do';
import { MatDialog, MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { FirstLoginDialogComponent } from './first-login-dialog/first-login-dialog.component';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private authService: AuthService;
  private snackBar: MatSnackBar;
  private dialog: MatDialog;

  constructor(private injector: Injector) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    this.authService = this.injector.get(AuthService);
    this.snackBar = this.injector.get(MatSnackBar);
    this.snackBar = this.injector.get(MatSnackBar);
    this.dialog = this.injector.get(MatDialog);

    let token = this.authService.getToken();
    if (token) {
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
          this.showSnack('Brak autentykacji');
          if (err.message.indexOf('locked')) {
            this.showFirstLoginDialog();
          }
          this.showSnack('Brak autentykacji');
          this.authService.logout();
        } else if (err.status === 403) {
          this.showSnack('Brak uprawnień. Zaloguj się');
          this.authService.logout();
        } else {
          this.showSnack('Błąd serwera');
        }
      }
    });
  }

  private showSnack(message: string) {
    let snackConfig: MatSnackBarConfig = new MatSnackBarConfig();
    snackConfig.duration = 2000;

    this.snackBar.open(message, null, snackConfig);
  }

  private showFirstLoginDialog() {
    let dialogRef = this.dialog.open(FirstLoginDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {

        this.showSnack('i tutaj call do serwisu z pass: ' + result);
      }
    });
  }
}
