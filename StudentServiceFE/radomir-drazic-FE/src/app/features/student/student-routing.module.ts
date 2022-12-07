import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActiveexamsListComponent } from './pages/activeexams-list/activeexams-list.component';
import { AddStudentComponent } from './pages/add-student/add-student.component';
import { CheckExamComponent } from './pages/check-exam/check-exam.component';
import { PassedexamsListComponent } from './pages/passedexams-list/passedexams-list.component';
import { RemoveExamComponent } from './pages/remove-exam/remove-exam.component';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { StudentFormComponent } from './pages/student-form/student-form.component';
import { StudentListComponent } from './pages/student-list/student-list.component';

const routes: Routes = [
  {path: 'student-list', component: StudentListComponent},
  {path: 'student-form/:studentId', component: StudentFormComponent},
  {path: 'student-details/:studentId', component: StudentDetailsComponent,
  children:[
    { path:'passedexams-list', component: PassedexamsListComponent },
    { path:'activeexams-list', component: ActiveexamsListComponent },
    { path:'remove-exam/:id', component: RemoveExamComponent },
    { path:'check-exam', component: CheckExamComponent },
    { path:'', redirectTo: 'activeexams-list', pathMatch:'full' }]},
  {path: 'add-student', component: AddStudentComponent},
  {path:'', pathMatch: 'full', redirectTo:'student-list'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
