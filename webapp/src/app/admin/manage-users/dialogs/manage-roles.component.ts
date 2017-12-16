import { AfterViewInit, Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, Validators } from '@angular/forms';
import { Roles } from '../roles.const';
import { MatChipList } from '@angular/material';

@Component({
  selector: 'hr-manage-roles',
  templateUrl: './manage-roles.component.html'
})
export class ManageRolesComponent implements OnInit, AfterViewInit {
  @Input() roles: FormArray;
  @ViewChild(MatChipList) chipList: MatChipList;
  possibleRoles: string[] = Roles;

  ngOnInit() {
    if (!this.roles) {
      this.roles = new FormArray([], Validators.required);
    }
  }

  ngAfterViewInit(): void {
    console.log(this.chipList);
    this.chipList.chipSelectionChanges.subscribe(change => {
      console.log(change);
    });
  }

  onChipSelect(chip) {
    if (!this.roles.controls.find(control => control.value === chip)) {
      this.roles.push(new FormControl(chip));
    }
  }

  removeRole(role: FormControl): void {
    this.roles.removeAt(this.roles.controls.indexOf(role));
  }
}
