import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { PageRequest } from 'src/app/core/models/page-request.dto';
import { Professor } from 'src/app/core/models/professor';
import { HttpProfessorService } from 'src/app/core/services/http-professor.service';
import { ConfirmDeleteComponent } from 'src/app/shared/components/confirm-delete/confirm-delete.component';
import { AddProfessorComponent } from '../add-professor/add-professor.component';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
})
export class ProfessorListComponent implements OnInit {
  professors?: Professor[];
  subscription = new Subscription();
  pageInfo: PageRequest = {
    pageNo: 1,
    pageSize: 3,
    totalItems: 10,
    sortBy: 'lastName',
    sortOrder: 'asc',
  };
  availablePageSizes = [3, 5, 10, 15];

  constructor(
    private httpProfessor: HttpProfessorService,
    private activatedRoute: ActivatedRoute,
    private modalService: NgbModal,
    private router: Router
  ) {}

  ngOnInit(): void {
    const pageNoParam = Number(
      this.activatedRoute.snapshot.queryParamMap.get('pageNo')
    );
    if (pageNoParam) {
      this.pageInfo.pageNo = pageNoParam;
      this.pageInfo.pageSize = Number(
        this.activatedRoute.snapshot.queryParamMap.get('pageSize')
      );
    }
    this.loadProfessors();
  }

  deleteProfessor(professor: Professor) {
    this.subscription.add(
      this.httpProfessor.deleteProfessor(professor).subscribe(() => {
        this.professors = this.professors?.filter(
          (p) => p.id! !== professor.id
        )
        this.router.navigate(['/professors']);
      })
    );
  }

  onDeleteProfessor(professor: Professor) {
    const modalRef = this.modalService.open(ConfirmDeleteComponent);
    modalRef.componentInstance.message = 'Do you want to delete Professor';
    modalRef.componentInstance.header =
      professor.firstName + ' ' + professor.lastName;
    modalRef.result.then(
      (result) => result === 'OK' && this.deleteProfessor(professor)
    );
  }
  onAddProfessor() {
    const modalRef = this.modalService.open(AddProfessorComponent);
    modalRef.result.then((result) => result === 'OK');
  }

  loadProfessors() {
    this.subscription.add(
      this.httpProfessor.getByPage(this.pageInfo).subscribe((professorPage) => {
        this.professors = professorPage.content;
        this.pageInfo.totalItems = professorPage.totalElements;
        this.pageInfo.pageSize = professorPage.size;
        this.pageInfo.pageNo = professorPage.number + 1;
      })
    );
  }

  onPageChange(pageNo: number) {
    this.loadProfessors();
  }

  onPageSizeChange() {
    this.pageInfo.pageNo = 1;
    this.loadProfessors();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
