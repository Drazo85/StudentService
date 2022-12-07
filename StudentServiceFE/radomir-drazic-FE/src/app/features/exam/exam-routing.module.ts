import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivestudentsListComponent } from './pages/activestudents-list/activestudents-list.component';
import { AddExamComponent } from './pages/add-exam/add-exam.component';
import { ExamDetailsComponent } from './pages/exam-details/exam-details.component';
import { ExamFormComponent } from './pages/exam-form/exam-form.component';
import { ExamListComponent } from './pages/exam-list/exam-list.component';

const routes: Routes = [
  {path: 'exam-list', component: ExamListComponent},
  {path: 'exam-form/:id', component: ExamFormComponent},
  {path: 'add-exam', component: AddExamComponent},
  {path: 'exam-details/:id', component: ExamDetailsComponent,
  children:[
    { path:'activestudents-list', component: ActivestudentsListComponent},
    { path:'', redirectTo: 'activestudents-list', pathMatch:'full' }
  ]
  },
  {path:'', pathMatch: 'full', redirectTo:'exam-list'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamRoutingModule { }
