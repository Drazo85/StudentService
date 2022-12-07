import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProfessorComponent } from './pages/add-professor/add-professor.component';
import { ProfessorDetailsComponent } from './pages/professor-details/professor-details.component';
import { ProfessorFormComponent } from './pages/professor-form/professor-form.component';
import { ProfessorListComponent } from './pages/professor-list/professor-list.component';
import { ProfessoractiveexamsListComponent } from './pages/professoractiveexams-list/professoractiveexams-list.component';

const routes: Routes = [
  {path: 'professor-list', component: ProfessorListComponent},
  {path: 'professor-form/:id', component: ProfessorFormComponent},
  {path: 'add-professor', component: AddProfessorComponent},
  {path: 'professor-details/:id', component: ProfessorDetailsComponent,
    children:[
      { path:'professoractiveexams-list', component: ProfessoractiveexamsListComponent},
    ]
  },
  {path:'', pathMatch: 'full', redirectTo:'professor-list'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessorRoutingModule { }
