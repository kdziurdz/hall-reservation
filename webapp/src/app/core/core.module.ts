import { NgModule } from '@angular/core';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { SharedModule } from '../shared/shared.module';
import { AuthService } from './auth/auth.service';
import { TokenInterceptor } from './auth/token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { AuthGuard } from './auth/auth.guard';
import { LoginGuard } from './auth/login.guard';
import { LessonDateTimeService } from './service/lesson-date-time.service';
import { UserService } from './auth/user/user.service';
import { FirstLoginDialogComponent } from './auth/first-login-dialog/first-login-dialog.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReservationService } from './service/reservation.service';

@NgModule({
  declarations: [
    ToolbarComponent,
    FirstLoginDialogComponent,
  ],
  entryComponents: [
    FirstLoginDialogComponent
  ],
  providers: [
    AuthService,
    AuthGuard,
    LoginGuard,
    LessonDateTimeService,
    UserService,
    ReservationService
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    ToolbarComponent
  ]
})
export class CoreModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [
        AuthGuard,
        AuthService,
        LoginGuard,
        LessonDateTimeService,
        UserService,
        ReservationService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: TokenInterceptor,
          multi: true
        }
      ]
    };
  }
}
