import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { LoginFormComponent } from './shared/components/login-form/login-form.component';
import { AuthGuard } from './core/auth/auth.guard';
import { PageNotFoundComponent } from './shared/components/page-not-found/page-not-found.component';
import { LoginGuard } from './core/auth/login.guard';
import {AboutComponent} from "./shared/components/about/about.component";

const appRoutes: Routes = [
  {path: 'login', canActivate: [LoginGuard], component: LoginFormComponent},
  {path: 'about', component: AboutComponent },
  {path: 'page-not-found', component: PageNotFoundComponent},
  {path: 'reservation',
    loadChildren: 'app/reservation/reservation.module#ReservationModule',
    pathMatch: 'prefix'},
  {path: 'classesperiod',
    loadChildren: 'app/classesperiod/classesperiod.module#AdminModule',
    pathMatch: 'prefix'},
  {path: '', redirectTo: 'reservation', pathMatch: 'full'},
  {path: '**', redirectTo: 'page-not-found'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes, {enableTracing: false}
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
