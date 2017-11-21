import { NgModule } from '@angular/core';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    ToolbarComponent
  ],
  providers: [],
  imports: [
    SharedModule
  ],
  exports: [
    ToolbarComponent
  ]
})
export class CoreModule {

}
