import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PassedexamFormComponent } from './pages/passedexam-form/passedexam-form.component';
import { PassedexamListComponent } from './pages/passedexam-list/passedexam-list.component';

const routes: Routes = [
  {path: 'passedexam-list', component: PassedexamListComponent},
  {path: 'passedexam-form/:studentId/:id', component: PassedexamFormComponent},
  {path:'', pathMatch: 'full', redirectTo:'passedexam-list'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PassedexamRoutingModule { }
