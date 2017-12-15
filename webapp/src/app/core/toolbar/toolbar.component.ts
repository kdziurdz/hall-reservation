import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'hr-toolbar',
  styleUrls: ['toolbar.component.scss'],
  templateUrl: 'toolbar.component.html'
})
export class ToolbarComponent implements OnInit {

  @Output() onToggleSidenav: EventEmitter<any> = new EventEmitter<any>();
  isLogged: boolean;

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.isStillAuthenticated()
    .subscribe(isLogged => this.isLogged = isLogged);
  }

  toggleSidenav(): void {
    this.onToggleSidenav.emit();
  }

  logout(): void {
    this.authService.logout();
  }

}
