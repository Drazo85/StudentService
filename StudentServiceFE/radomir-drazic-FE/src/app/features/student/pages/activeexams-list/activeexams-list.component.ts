import { DatePipe, formatDate } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { HttpExamService } from 'src/app/core/services/http-exam.service';

@Component({
  selector: 'app-activeexams-list',
  templateUrl: './activeexams-list.component.html',
  styleUrls: ['./activeexams-list.component.css'],
})
export class ActiveexamsListComponent implements OnInit {
  studentId!: number;
  exams!: Exam[];
  examId!: number;
  subscription = new Subscription;
  today = new Date().toISOString().split('T')[0];

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpExam: HttpExamService
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
      this.httpExam.getActiveExams(this.studentId).subscribe((exams) => {
        this.exams = exams;
      })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
