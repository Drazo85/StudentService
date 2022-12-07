import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { HttpExamTermService } from 'src/app/core/services/http-exam-term.service';

@Component({
  selector: 'app-examterm-details',
  templateUrl: './examterm-details.component.html',
  styleUrls: ['./examterm-details.component.css'],
})
export class ExamtermDetailsComponent implements OnInit {
  examTerm?: ExamTerm;
  examTermId!: number;
  subscription = new Subscription();

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpExamTerm: HttpExamTermService
  ) {}

  ngOnInit(): void {
    this.examTermId = Number(this.activatedRoute.snapshot.params['examTermId']);
    this.getExamTerm();
  }

  getExamTerm() {
    this.subscription.add(
      this.httpExamTerm
        .getById(this.examTermId)
        .subscribe((examTerm) => (this.examTerm = examTerm))
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
