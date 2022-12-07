import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamtermRoutingModule } from './examterm-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ExamtermListComponent } from './pages/examterm-list/examterm-list.component';
import { ExamtermFormComponent } from './pages/examterm-form/examterm-form.component';
import { AddExamtermComponent } from './pages/add-examterm/add-examterm.component';
import { ExamtermDetailsComponent } from './pages/examterm-details/examterm-details.component';
import { ExamsListComponent } from './pages/exams-list/exams-list.component';


@NgModule({
  declarations: [
    ExamtermListComponent,
    ExamtermFormComponent,
    AddExamtermComponent,
    ExamtermDetailsComponent,
    ExamsListComponent
  ],
  imports: [
    CommonModule,
    ExamtermRoutingModule,
    SharedModule
  ]
})
export class ExamtermModule { }
