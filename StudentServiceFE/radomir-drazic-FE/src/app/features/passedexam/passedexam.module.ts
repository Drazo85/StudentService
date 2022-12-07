import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PassedexamRoutingModule } from './passedexam-routing.module';
import { PassedexamListComponent } from './pages/passedexam-list/passedexam-list.component';
import { PassedexamFormComponent } from './pages/passedexam-form/passedexam-form.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    PassedexamListComponent,
    PassedexamFormComponent
  ],
  imports: [
    CommonModule,
    PassedexamRoutingModule,
    SharedModule
  ]
})
export class PassedexamModule { }
