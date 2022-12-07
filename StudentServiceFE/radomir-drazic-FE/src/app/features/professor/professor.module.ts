import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessorRoutingModule } from './professor-routing.module';
import { ProfessorListComponent } from './pages/professor-list/professor-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProfessorFormComponent } from './pages/professor-form/professor-form.component';
import { AddProfessorComponent } from './pages/add-professor/add-professor.component';
import { ProfessorDetailsComponent } from './pages/professor-details/professor-details.component';
import { ProfessoractiveexamsListComponent } from './pages/professoractiveexams-list/professoractiveexams-list.component';


@NgModule({
  declarations: [
    ProfessorListComponent,
    ProfessorFormComponent,
    AddProfessorComponent,
    ProfessorDetailsComponent,
    ProfessoractiveexamsListComponent
  ],
  imports: [
    CommonModule,
    ProfessorRoutingModule,
    SharedModule
  ]
})
export class ProfessorModule { }
