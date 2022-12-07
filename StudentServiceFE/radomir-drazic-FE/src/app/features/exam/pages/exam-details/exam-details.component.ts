import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { Student } from 'src/app/core/models/student';
import { HttpExamService } from 'src/app/core/services/http-exam.service';

@Component({
  selector: 'app-exam-details',
  templateUrl: './exam-details.component.html',
  styleUrls: ['./exam-details.component.css'],
})
export class ExamDetailsComponent implements OnInit {
  exam?: Exam;
  id!: number;
  students?: Student;
  subscription = new Subscription();

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpExamService: HttpExamService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.snapshot.params['id']);
    this.getExam();
  }

  getExam() {
    this.subscription.add(
      this.httpExamService
        .getById(this.id)
        .subscribe((exam) => (this.exam = exam))
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
