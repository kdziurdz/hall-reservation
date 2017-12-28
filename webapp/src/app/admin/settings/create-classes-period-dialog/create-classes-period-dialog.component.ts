import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CreateClassesPeriodDialogResult } from './create-classes-period-dialog-result';
import { LessonDateTimeService } from '../../../core/service/lesson-date-time.service';

@Component({
  templateUrl: './create-classes-period-dialog.component.html',
})
export class CreateClassesPeriodDialogComponent implements OnInit {

  createPeriodFormGroup: FormGroup;
  todayDate: Date;

  constructor(private dialogRef: MatDialogRef<CreateClassesPeriodDialogComponent>,
              private lessonDateTimeService: LessonDateTimeService) {
  }

  ngOnInit() {
    this.todayDate = new Date();

    this.createPeriodFormGroup = new FormGroup({
      dateFrom: new FormControl(null, Validators.required),
      dateTo: new FormControl(null, Validators.required)
    })
  }

  dismiss(): void {
    this.dialogRef.close(false);
  }

  submit(): void {
    let values = this.createPeriodFormGroup.getRawValue();
    let result: CreateClassesPeriodDialogResult = {
      dateFrom: this.lessonDateTimeService.getDateAsString(values.dateFrom),
      dateTo: this.lessonDateTimeService.getDateAsString(values.dateTo)
    };
    this.dialogRef.close(result);
  }

}
