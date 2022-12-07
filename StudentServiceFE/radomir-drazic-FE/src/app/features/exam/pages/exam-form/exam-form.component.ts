import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RoutesRecognized } from '@angular/router';
import { Subscription, throwIfEmpty } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { Subject } from 'src/app/core/models/subject';
import { HttpExamTermService } from 'src/app/core/services/http-exam-term.service';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { HttpSubjectService } from 'src/app/core/services/http-subject.service';

@Component({
  selector: 'app-exam-form',
  templateUrl: './exam-form.component.html',
  styleUrls: ['./exam-form.component.css'],
})
export class ExamFormComponent implements OnInit {
  exam!: Exam;
  examId!: number;
  examTerms?: ExamTerm[];
  subjects?: Subject[];
  subscription = new Subscription();

  constructor(
    private httpExam: HttpExamService,
    private httpExamTerm: HttpExamTermService,
    private httpSubject: HttpSubjectService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.examId = Number(this.activatedRoute.snapshot.params['id']);
    this.getExam();
    this.loadExamTerms();
    this.loadSubjects();
  }

  getExam() {
    this.httpExam.getById(this.examId).subscribe((exam) => (this.exam = exam));
  }

  updateExam() {
    this.subscription.add(
      this.httpExam.editExam(this.exam).subscribe((exam) => {
        this.exam = exam;
        this.router.navigate(['/exams']);
      })
    );
  }

  loadExamTerms() {
    this.subscription.add(
      this.httpExamTerm
        .getAll()
        .subscribe((examTerms) => (this.examTerms = examTerms))
    );
  }

  loadSubjects() {
    this.subscription.add(
      this.httpSubject
        .getAll()
        .subscribe((subjects) => (this.subjects = subjects))
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
