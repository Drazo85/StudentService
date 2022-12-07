import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Student } from 'src/app/core/models/student';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { HttpStudentService } from 'src/app/core/services/http-student.service';

@Component({
  selector: 'app-activestudents-list',
  templateUrl: './activestudents-list.component.html',
  styleUrls: ['./activestudents-list.component.css'],
})
export class ActivestudentsListComponent implements OnInit {
  id!: number;
  students!: Student[];
  student!: Student;
  studentId!: number;
  subscription = new Subscription();

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpStudent: HttpStudentService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.parent?.snapshot.params['id']);
    this.studentId = Number(this.activatedRoute.snapshot.params['studentId']);
    this.loadStudents();
  }

  loadStudents() {
    this.subscription.add(
      this.httpStudent
        .getActiveStudents(this.id)
        .subscribe((students) => (this.students = students))
    );
  }
}
