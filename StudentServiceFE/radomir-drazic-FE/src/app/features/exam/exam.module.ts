import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamRoutingModule } from './exam-routing.module';
import { AddExamComponent } from './pages/add-exam/add-exam.component';
import { ExamFormComponent } from './pages/exam-form/exam-form.component';
import { ExamListComponent } from './pages/exam-list/exam-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ExamDetailsComponent } from './pages/exam-details/exam-details.component';
import { ActivestudentsListComponent } from './pages/activestudents-list/activestudents-list.component';


@NgModule({
  declarations: [
    AddExamComponent,
    ExamFormComponent,
    ExamListComponent,
    ExamDetailsComponent,
    ActivestudentsListComponent
  ],
  imports: [
    CommonModule,
    ExamRoutingModule,
    SharedModule
  ]
})
export class ExamModule { }
