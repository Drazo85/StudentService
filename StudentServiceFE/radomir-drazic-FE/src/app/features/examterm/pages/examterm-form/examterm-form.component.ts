import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { HttpExamTermService } from 'src/app/core/services/http-exam-term.service';

@Component({
  selector: 'app-examterm-form',
  templateUrl: './examterm-form.component.html',
  styleUrls: ['./examterm-form.component.css'],
})
export class ExamtermFormComponent implements OnInit {
  examTerm!: ExamTerm;
  examTermId!: number;
  subscription = new Subscription();

  constructor(
    private httpExamTerm: HttpExamTermService,
    private activatedRoute: ActivatedRoute,
    private router: Router
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

  updateExamTerm() {
    this.subscription.add(
      this.httpExamTerm.editExamTerm(this.examTerm).subscribe((examTerm) => {
        this.examTerm = examTerm;
        this.router.navigate(['/examterms']);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
