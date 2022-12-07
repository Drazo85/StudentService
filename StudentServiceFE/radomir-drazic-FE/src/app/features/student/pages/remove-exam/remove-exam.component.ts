import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { Student } from 'src/app/core/models/student';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { HttpStudentService } from 'src/app/core/services/http-student.service';

@Component({
  selector: 'app-remove-exam',
  templateUrl: './remove-exam.component.html',
  styleUrls: ['./remove-exam.component.css'],
})
export class RemoveExamComponent implements OnInit {
  exam!: Exam;
  studentId!: number;
  exams!: Exam[];
  student!: Student;
  examId!: number;
  subscription = new Subscription();

  constructor(
    private httpExam: HttpExamService,
    private httpStudent: HttpStudentService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.studentId = Number(
      this.activatedRoute.parent?.snapshot.params['studentId']
    );
    this.examId = Number(this.activatedRoute.snapshot.params['id']);
    this.loadExam();
  }

  loadExams() {
    this.subscription.add(
      this.httpExam.getAll().subscribe((exams) => (this.exams = exams))
    );
  }

  loadExam() {
    this.subscription.add(
      this.httpExam.getById(this.examId).subscribe((exam) => (this.exam = exam))
    );
  }

  loadStudent() {
    this.subscription.add(
      this.httpStudent
        .getById(this.studentId)
        .subscribe((student) => (this.student = student))
    );
  }

  removeActiveExam() {
    this.subscription.add(
      this.httpExam
        .removeExam(this.exam.id!, this.studentId)
        .subscribe((exam) => {
          this.exam = exam;
          this.router.navigate(['/students']);
        })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
