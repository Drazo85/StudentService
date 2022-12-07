import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { HttpExamTermService } from 'src/app/core/services/http-exam-term.service';

@Component({
  selector: 'app-add-examterm',
  templateUrl: './add-examterm.component.html',
  styleUrls: ['./add-examterm.component.css'],
})
export class AddExamtermComponent implements OnInit {
  subscription = new Subscription();

  examTerm: ExamTerm = {
    name: '',
    startDate: new Date(),
    endDate: new Date(),
    isActive: false,
  };

  constructor(
    private httpExamTerm: HttpExamTermService,
    private router: Router,
    public modal: NgbActiveModal
  ) {}

  ngOnInit(): void {}

  onAddExamTerm() {
    this.subscription.add(
      this.httpExamTerm.addExamTerm(this.examTerm).subscribe((examTerm) => {
        this.examTerm = examTerm
        this.modal.close();
        this.router.navigate(['/examterms']);
      })
    );

  }

  onCancel() {
    this.modal.close();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
