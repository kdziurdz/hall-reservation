import { Component, ViewChild } from '@angular/core';
import { MediaChange, ObservableMedia } from '@angular/flex-layout';
import { MatSidenav } from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  mode: string = 'side';
  opened: boolean = true;

  @ViewChild(MatSidenav) sidenav: MatSidenav;

  constructor(private media: ObservableMedia) {
    this.media.subscribe((mediaChange: MediaChange) => {
      this.mode = this.getMode(mediaChange);
      this.opened = this.getOpened(mediaChange);
    });
  }

  toggleSidenav() {
    this.sidenav.toggle()
  }

  private getMode(mediaChange: MediaChange): string {
    // set mode based on a breakpoint
    if (this.media.isActive('gt-sm')) {
      return 'side';
    } else {
      return 'over';
    }
  }
  // open/close as needed
  private getOpened(mediaChange: MediaChange): boolean {
    if (this.media.isActive('gt-sm')) {
      return true;
    } else {
      return false;
    }
  }
}
