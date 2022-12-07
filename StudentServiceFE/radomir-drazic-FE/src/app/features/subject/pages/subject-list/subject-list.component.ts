import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { PageRequest } from 'src/app/core/models/page-request.dto';
import { Subject } from 'src/app/core/models/subject';
import { HttpSubjectService } from 'src/app/core/services/http-subject.service';
import { ConfirmDeleteComponent } from 'src/app/shared/components/confirm-delete/confirm-delete.component';
import { AddSubjectComponent } from '../add-subject/add-subject.component';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css'],
})
export class SubjectListComponent implements OnInit {
  subjects?: Subject[];
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
    private httpSubject: HttpSubjectService,
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
    this.loadSubjects();
  }

  deleteSelectedSubject(subject: Subject) {
    this.subscription.add(
      this.httpSubject.deleteSubject(subject).subscribe(() => {
        this.subjects = this.subjects?.filter(
          (s) => s.subjectId! !== subject.subjectId
        );
        this.router.navigate(['/subjects']);
      })
    );
  }

  onDeleteSubject(subject: Subject) {
    const modalRef = this.modalService.open(ConfirmDeleteComponent);
    (modalRef.componentInstance.message = 'Do you want to delete Subject'),
      subject.name;
    modalRef.componentInstance.header = subject.name;
    modalRef.result.then(
      (result) => result === 'OK' && this.deleteSelectedSubject(subject)
    );
  }

  onAddSubject() {
    const modalRef = this.modalService.open(AddSubjectComponent);
    modalRef.result.then((result) => result === 'OK');
  }

  loadSubjects() {
    this.subscription.add(
      this.httpSubject.getByPage(this.pageInfo).subscribe((subjectPage) => {
        this.subjects = subjectPage.content;
        this.pageInfo.totalItems = subjectPage.totalElements;
        this.pageInfo.pageSize = subjectPage.size;
        this.pageInfo.pageNo = subjectPage.number + 1;
      })
    );
  }

  onPageChange(pageNo: number) {
    this.loadSubjects();
  }
  onPageSizeChange() {
    this.pageInfo.pageNo = 1;
    this.loadSubjects();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
