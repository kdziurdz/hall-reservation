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
import { HasAnyRoleDirective } from '../shared/directives/has-any-role/has-any-role.directive';
import { HasRoleDirective } from '../shared/directives/has-role/has-role.directive';
import { UserService } from './auth/user/user.service';

@NgModule({
  declarations: [
    ToolbarComponent
  ],
  providers: [
    AuthService,
    AuthGuard,
    LoginGuard,
    LessonDateTimeService,
    UserService
  ],
  imports: [
    SharedModule
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
        {
          provide: HTTP_INTERCEPTORS,
          useClass: TokenInterceptor,
          multi: true
        }
       ]
    };
  }
}
