import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { ConfirmDeleteComponent } from 'src/app/shared/components/confirm-delete/confirm-delete.component';
import { AddExamComponent } from '../add-exam/add-exam.component';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css'],
})
export class ExamListComponent implements OnInit {
  exams?: Exam[];
  isActive = true;
  subscription = new Subscription;

  constructor(
    private httpExam: HttpExamService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.subscription.add(
      this.httpExam
        .getAll()
        .subscribe(
          (exams) =>
            (this.exams = exams.filter(
              (exam) => (this.isActive = exam.examTerm.isActive)
            ))
        )
    );
  }

  deleteExam(exam: Exam) {
    this.httpExam
      .deleteExam(exam)
      .subscribe(
        () => (this.exams = this.exams?.filter((ex) => ex.id !== exam.id))
      );
  }

  onDeleteExam(exam: Exam) {
    const modalRef = this.modalService.open(ConfirmDeleteComponent);
    modalRef.componentInstance.message = 'Do you want to delete Exam';
    modalRef.componentInstance.header = exam.subject.name;
    modalRef.result.then((result) => result === 'Ok' && this.deleteExam(exam));
  }

  onAddExam() {
    const modalRef = this.modalService.open(AddExamComponent);
    modalRef.result.then((result) => result === 'Ok');
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
