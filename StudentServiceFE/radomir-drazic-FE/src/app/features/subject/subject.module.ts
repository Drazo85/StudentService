import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SubjectRoutingModule } from './subject-routing.module';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { SubjectFormComponent } from './pages/subject-form/subject-form.component';
import { AddSubjectComponent } from './pages/add-subject/add-subject.component';


@NgModule({
  declarations: [
    SubjectListComponent,
    SubjectFormComponent,
    AddSubjectComponent
  ],
  imports: [
    CommonModule,
    SubjectRoutingModule,
    SharedModule
  ]
})
export class SubjectModule { }
