import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddExamtermComponent } from './pages/add-examterm/add-examterm.component';
import { ExamsListComponent } from './pages/exams-list/exams-list.component';
import { ExamtermDetailsComponent } from './pages/examterm-details/examterm-details.component';
import { ExamtermFormComponent } from './pages/examterm-form/examterm-form.component';
import { ExamtermListComponent } from './pages/examterm-list/examterm-list.component';

const routes: Routes = [
  {path: 'examterm-list', component: ExamtermListComponent},
  {path: 'examterm-form/:examTermId', component: ExamtermFormComponent},
  {path: 'examterm-details/:examTermId', component: ExamtermDetailsComponent,
  children:[
    { path:'exams-list', component: ExamsListComponent },
    { path:'', redirectTo: 'exams-list', pathMatch:'full' }]},
  {path: 'add-examterm', component: AddExamtermComponent},
  {path:'', pathMatch: 'full', redirectTo:'examterm-list'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamtermRoutingModule { }
