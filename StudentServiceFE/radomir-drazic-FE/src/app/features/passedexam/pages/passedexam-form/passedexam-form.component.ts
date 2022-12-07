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
  selector: 'app-passedexam-form',
  templateUrl: './passedexam-form.component.html',
  styleUrls: ['./passedexam-form.component.css']
})
export class PassedexamFormComponent implements OnInit {

  exam!: Exam;
  examId!: number;
  studentId!: number;
  student!: Student;
  passedExam: PassedExam
   = {
     exam: this.exam,
     student: this.student,
     grade: 6
   };
  subscription = new Subscription;

  constructor(private httpExam: HttpExamService,
    private activatedRoute: ActivatedRoute,
    private httpPassedExam: HttpPassedexamService,
    private httpStudent: HttpStudentService,
    private router: Router) { }

  ngOnInit(): void {
    this.studentId = Number(this.activatedRoute.snapshot.params['studentId'])
    this.examId = Number(this.activatedRoute.snapshot.params['id']);
    this.loadExam();
    this.loadStudent();
  }

  loadExam(){
    this.subscription.add(
      this.httpExam.getById(this.examId)
      .subscribe(
        (exam) => {
          this.exam = exam
        }
      )
    )
  }

  loadStudent(){
    this.subscription.add(
      this.httpStudent.getById(this.studentId).subscribe(
        (student) => {
          this.student = student
        }
      )
    )
  }

  onAddPassedExam(){
    this.subscription.add(
      this.httpPassedExam.addExam(this.passedExam)
      .subscribe(
        (passedExam) => {
          this.passedExam = passedExam;
          this.router.navigate(['/students']);
          }
        )
      )
    }

    compareFnExam(exam1: Exam, exam2: Exam) : boolean{
      return exam1.id === exam2.id
    }

    compareFnStudent(student1: Student, student2: Student) : boolean{
      return student1.studentId === student2.studentId
    }

    ngOnDestroy(): void {
      this.subscription.unsubscribe();
    }
}
