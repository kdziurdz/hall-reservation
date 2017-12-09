import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from './material.module';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HasAnyRoleDirective } from './directives/has-any-role/has-any-role.directive';
import { HasRoleDirective } from './directives/has-role/has-role.directive';

@NgModule({
  declarations: [
    LoginFormComponent,
    PageNotFoundComponent,
    HasAnyRoleDirective,
    HasRoleDirective
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
    HasRoleDirective
  ]
})
export class SharedModule {

}
