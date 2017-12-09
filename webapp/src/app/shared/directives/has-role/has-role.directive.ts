import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import { UserService } from '../../../core/auth/user/user.service';
import { AuthService } from '../../../core/auth/auth.service';

@Directive({selector: '[hrHasRole]'})
export class HasRoleDirective {

  private hasView = false;

  constructor(private templateRef: TemplateRef<any>,
              private viewContainer: ViewContainerRef,
              private userService: UserService,
              private authService: AuthService) {
  }

  @Input() set hrHasRole(roles: string) {

    this.authService.isStillAuthenticated().subscribe(isAuthenticated => {
      if(isAuthenticated) {
        roles = roles.replace(/ /g, '');
        if (this.userService.hasAllRoles(roles.split(',')) && !this.hasView) {
          this.createView();
        } else {
          this.destroyView();
        }
      } else {
        this.destroyView();
      }
    });
  }

  private destroyView(): void {
    this.viewContainer.clear();
    this.hasView = false;
  }

  private createView() {
    this.viewContainer.createEmbeddedView(this.templateRef);
    this.hasView = true;
  }
}
