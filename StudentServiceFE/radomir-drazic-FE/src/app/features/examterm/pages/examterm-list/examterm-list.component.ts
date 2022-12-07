import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { PageRequest } from 'src/app/core/models/page-request.dto';
import { HttpExamTermService } from 'src/app/core/services/http-exam-term.service';
import { ConfirmDeleteComponent } from 'src/app/shared/components/confirm-delete/confirm-delete.component';
import { AddExamtermComponent } from '../add-examterm/add-examterm.component';

@Component({
  selector: 'app-examterm-list',
  templateUrl: './examterm-list.component.html',
  styleUrls: ['./examterm-list.component.css'],
})
export class ExamtermListComponent implements OnInit {
  examTerms?: ExamTerm[];
  subscription = new Subscription();
  pageInfo: PageRequest = {
    pageNo: 1,
    pageSize: 3,
    totalItems: 10,
    sortBy: 'name',
    sortOrder: 'asc',
  };
  availablePageSizes = [3, 5, 10, 15];

  constructor(
    private httpExamTerm: HttpExamTermService,
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
    this.loadExamTerms();
  }

  deleteExamTerm(examTerm: ExamTerm) {
    this.subscription.add(
      this.httpExamTerm.deleteExamTerm(examTerm).subscribe(() => {
        this.examTerms = this.examTerms?.filter(
          (ex) => ex.examTermId !== examTerm.examTermId
        );
        this.router.navigate(['/examterms']);
      })
    );
  }

  onDeleteExamTerm(examTerm: ExamTerm) {
    const modalRef = this.modalService.open(ConfirmDeleteComponent);
    modalRef.componentInstance.message = 'Do you want to delete Exam term?';
    modalRef.componentInstance.header = examTerm.name;
    modalRef.result.then(
      (result) => result === 'OK' && this.deleteExamTerm(examTerm)
    );
  }

  onAddExamTerm() {
    const modalRef = this.modalService.open(AddExamtermComponent);
    modalRef.result.then((result) => result === 'OK');
  }

  loadExamTerms() {
    this.subscription.add(
      this.httpExamTerm.getByPage(this.pageInfo).subscribe((examTermPage) => {
        this.examTerms = examTermPage.content;
        this.pageInfo.totalItems = examTermPage.totalElements;
        this.pageInfo.pageSize = examTermPage.size;
        this.pageInfo.pageNo = examTermPage.number + 1;
      })
    );
  }

  onPageChange(pageNo: number) {
    this.loadExamTerms();
  }
  onPageSizeChange() {
    this.pageInfo.pageNo = 1;
    this.loadExamTerms();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
