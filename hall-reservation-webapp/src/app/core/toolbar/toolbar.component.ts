import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'hr-toolbar',
  styleUrls: ['toolbar.component.scss'],
  templateUrl: 'toolbar.component.html'
})
export class ToolbarComponent {

  @Output() onToggleSidenav: EventEmitter<any> = new EventEmitter<any>()

  toggleSidenav(): void {
    this.onToggleSidenav.emit();
  }

}
