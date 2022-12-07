import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { AddExamComponent } from 'src/app/features/exam/pages/add-exam/add-exam.component';

@Component({
  selector: 'app-exams-list',
  templateUrl: './exams-list.component.html',
  styleUrls: ['./exams-list.component.css'],
})
export class ExamsListComponent implements OnInit {
  examTermId!: number;
  exams!: Exam[];
  examTerm!: ExamTerm;
  p: any;
  noPerPage: number = 3;

  subscription = new Subscription;

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpExam: HttpExamService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.examTermId = Number(
      this.activatedRoute.parent?.snapshot.params['examTermId']
    );
    this.loadExamsById();
  }

  loadExamsById() {
    this.subscription.add(
      this.httpExam
        .getAll()
        .subscribe(
          (exams) =>
            (this.exams = exams.filter(
              (exam) => this.examTermId == exam.examTerm.examTermId
            ))
        )
    );
  }
  onAddExam() {
    const modalRef = this.modalService.open(AddExamComponent);
    modalRef.result.then((result) => result === 'Ok');
  }

  onDeleteExam(exam: Exam) {
    this.subscription.add(
      this.httpExam
        .deleteExam(exam)
        .subscribe(
          () => (this.exams = this.exams?.filter((ex) => ex.id !== exam.id))
        )
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
