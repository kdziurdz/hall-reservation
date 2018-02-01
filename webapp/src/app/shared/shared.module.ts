import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from './material.module';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HasAnyRoleDirective } from './directives/has-any-role/has-any-role.directive';
import { HasRoleDirective } from './directives/has-role/has-role.directive';
import { AboutComponent } from './components/about/about.component';
import { ReservationCancellationDialog } from './components/reservation-cancellation-dialog/reservation-cancellation-dialog.component';
import { CancellationInfoDialog } from './components/cancellation-info-dialog/cancellation-info-dialog.component';
import { MyAccountFormComponent } from './components/my-account-form/my-account-form.component';

@NgModule({
  declarations: [
    LoginFormComponent,
    PageNotFoundComponent,
    HasAnyRoleDirective,
    HasRoleDirective,
    AboutComponent,
    ReservationCancellationDialog,
    CancellationInfoDialog,
    MyAccountFormComponent
  ],
  entryComponents: [
    ReservationCancellationDialog,
    CancellationInfoDialog
  ],
  imports: [
    FlexLayoutModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    FlexLayoutModule,
    MaterialModule,
    LoginFormComponent,
    PageNotFoundComponent,
    HasAnyRoleDirective,
    HasRoleDirective,
    AboutComponent
  ]
})
export class SharedModule {

}
