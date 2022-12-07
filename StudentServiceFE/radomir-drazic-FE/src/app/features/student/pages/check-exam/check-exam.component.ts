import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { PassedExam } from 'src/app/core/models/passedexam';
import { Student } from 'src/app/core/models/student';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { HttpPassedexamService } from 'src/app/core/services/http-passedexam.service';
import { HttpStudentService } from 'src/app/core/services/http-student.service';

@Component({
  selector: 'app-check-exam',
  templateUrl: './check-exam.component.html',
  styleUrls: ['./check-exam.component.css'],
})
export class CheckExamComponent implements OnInit {
  exam!: Exam;
  studentId!: number;
  exams!: Exam[];
  student!: Student;
  examId!: number;
  subscription = new Subscription();
  isActive = true;
  passedExams!: PassedExam[];

  constructor(
    private httpExam: HttpExamService,
    private httpStudent: HttpStudentService,
    private activatedRoute: ActivatedRoute,
    private httpPassedExam: HttpPassedexamService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.studentId = Number(
      this.activatedRoute.parent?.snapshot.params['studentId']
    );
    this.examId = Number(this.activatedRoute.snapshot.params['id']);
    this.loadExams();
  }

  loadExams() {
    this.subscription.add(
      this.httpExam
        .getExamsByYear(this.studentId)
        .subscribe(
          (exams) =>
            (this.exams = exams.filter(
              (exam) => (this.isActive = exam.examTerm.isActive)
            ))
        )
    );
  }

  loadPassedExams(){
    this.subscription.add(
      this.httpPassedExam.getAll()
        .subscribe(
          (passedExams) => (this.passedExams = passedExams)
        )
    )
  }
  loadStudent() {
    this.subscription.add(
      this.httpStudent
        .getById(this.studentId)
        .subscribe((student) => (this.student = student))
    );
  }

  loadExam() {
    this.subscription.add(
      this.httpExam.getById(this.examId).subscribe((exam) => (this.exam = exam))
    );
  }

  onCheckExam() {
    this.subscription.add(
      this.httpExam.checkExam(this.exam, this.studentId).subscribe((exam) => {
        this.exam = exam;
        this.router.navigate(['/students']);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
