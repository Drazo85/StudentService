import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddSubjectComponent } from './pages/add-subject/add-subject.component';
import { SubjectFormComponent } from './pages/subject-form/subject-form.component';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';

const routes: Routes = [
  {path: 'subject-list', component: SubjectListComponent},
  {path: 'subject-form/:subjectId', component: SubjectFormComponent},
  {path: 'add-subject', component: AddSubjectComponent},
  {path:'', pathMatch: 'full', redirectTo:'subject-list'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }
