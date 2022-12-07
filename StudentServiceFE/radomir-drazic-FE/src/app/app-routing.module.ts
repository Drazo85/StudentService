import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRoles } from './core/enums/enums';
import { AuthRolesGuard } from './core/guards/auth-roles.guard';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {path: 'home',
    loadChildren: () => import('./features/home/home.module').then((m) => m.HomeModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  { path: 'subjects',
    loadChildren: () => import('./features/subject/subject.module').then((m) => m.SubjectModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  { path: 'professors',
    loadChildren: () => import('./features/professor/professor.module').then((m) => m.ProfessorModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  { path: 'students',
    loadChildren: () => import('./features/student/student.module').then((m) => m.StudentModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  { path: 'examterms',
    loadChildren: () => import('./features/examterm/examterm.module').then((m) => m.ExamtermModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  { path: 'exams',
    loadChildren: () => import('./features/exam/exam.module').then((m) => m.ExamModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  { path: 'passedexams',
    loadChildren: () => import('./features/passedexam/passedexam.module').then((m) => m.PassedexamModule),
    canActivate: [AuthRolesGuard],
    data: {roles: [UserRoles.ROLE_ADMIN, UserRoles.ROLE_USER]}
  },
  {path: '', pathMatch:"full", redirectTo:'/login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
