import { Component, EventEmitter, Output } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'hr-toolbar',
  styleUrls: ['toolbar.component.scss'],
  templateUrl: 'toolbar.component.html'
})
export class ToolbarComponent {

  @Output() onToggleSidenav: EventEmitter<any> = new EventEmitter<any>()


  constructor(private authService: AuthService) {
  }

  toggleSidenav(): void {
    this.onToggleSidenav.emit();
  }

  logout(): void {
    this.authService.logout();
  }

}
