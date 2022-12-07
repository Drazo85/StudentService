import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { PassedExam } from 'src/app/core/models/passedexam';
import { HttpPassedexamService } from 'src/app/core/services/http-passedexam.service';

@Component({
  selector: 'app-passedexam-list',
  templateUrl: './passedexam-list.component.html',
  styleUrls: ['./passedexam-list.component.css'],
})
export class PassedexamListComponent implements OnInit {
  passedExams!: PassedExam[];
  subscription = new Subscription();

  constructor(private httpPassedExam: HttpPassedexamService) {}

  ngOnInit(): void {
    this.subscription.add(
      this.httpPassedExam
        .getAll()
        .subscribe((passedExams) => (this.passedExams = passedExams))
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
