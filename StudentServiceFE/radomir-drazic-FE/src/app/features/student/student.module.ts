import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { StudentListComponent } from './pages/student-list/student-list.component';
import { StudentFormComponent } from './pages/student-form/student-form.component';
import { AddStudentComponent } from './pages/add-student/add-student.component';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { PassedexamsListComponent } from './pages/passedexams-list/passedexams-list.component';
import { ActiveexamsListComponent } from './pages/activeexams-list/activeexams-list.component';
import { CheckExamComponent } from './pages/check-exam/check-exam.component';
import { RemoveExamComponent } from './pages/remove-exam/remove-exam.component';


@NgModule({
  declarations: [
    StudentListComponent,
    StudentFormComponent,
    AddStudentComponent,
    StudentDetailsComponent,
    PassedexamsListComponent,
    ActiveexamsListComponent,
    CheckExamComponent,
    RemoveExamComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    SharedModule
  ]
})
export class StudentModule { }
