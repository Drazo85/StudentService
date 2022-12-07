import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModalModule, NgbNavModule, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxPaginationModule } from 'ngx-pagination';
import { ConfirmDeleteComponent } from './components/confirm-delete/confirm-delete.component';
import { HeaderComponent } from './header/header.component';



@NgModule({
  declarations: [
    ConfirmDeleteComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModalModule,
    NgbPaginationModule,
    NgxPaginationModule,
    NgbNavModule
  ], exports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModalModule,
    NgbPaginationModule,
    NgxPaginationModule,
    HeaderComponent,
    NgbNavModule

  ]
})
export class SharedModule { }
