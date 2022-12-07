import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { Professor } from 'src/app/core/models/professor';
import { HttpProfessorService } from 'src/app/core/services/http-professor.service';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css'],
})
export class ProfessorDetailsComponent implements OnInit {
  professor?: Professor;
  id!: number;
  exams?: Exam[];
  subscription = new Subscription();

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpProfessor: HttpProfessorService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.snapshot.params['id']);
    this.getProfessor();
  }

  getProfessor() {
    this.subscription.add(
      this.httpProfessor
        .getById(this.id)
        .subscribe((professor) => (this.professor = professor))
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
