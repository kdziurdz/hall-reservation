import { NgModule } from '@angular/core';
import {SidebarNavComponent} from "./sidebar-nav/sidebar-nav.component";
import { MatIconModule, MatSidenavModule, MatToolbarModule } from '@angular/material';
import { TopNavbarComponent } from './top-navbar/top-navbar.component';

@NgModule({
  declarations: [
    SidebarNavComponent,
    TopNavbarComponent
  ],
  providers: [],
  imports: [
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule
  ],
  exports: [
    SidebarNavComponent,
    TopNavbarComponent
  ]
})
export class CoreModule {

}
