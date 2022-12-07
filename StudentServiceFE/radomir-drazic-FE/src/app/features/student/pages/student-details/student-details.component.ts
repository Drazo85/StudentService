import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/core/models/student';
import { Exam } from 'src/app/core/models/exam';
import { HttpStudentService } from 'src/app/core/services/http-student.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css'],
})
export class StudentDetailsComponent implements OnInit {
  student?: Student;
  studentId!: number;
  exams?: Exam[];
  subscription = new Subscription();

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpStudent: HttpStudentService
  ) {}

  ngOnInit(): void {
    this.studentId = Number(this.activatedRoute.snapshot.params['studentId']);
    this.getStudent();
  }

  getStudent() {
    this.subscription.add(
      this.httpStudent
        .getById(this.studentId)
        .subscribe((student) => (this.student = student))
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
