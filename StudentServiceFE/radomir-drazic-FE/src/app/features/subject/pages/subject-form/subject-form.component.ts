import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Professor } from 'src/app/core/models/professor';
import { Subject } from 'src/app/core/models/subject';
import { HttpProfessorService } from 'src/app/core/services/http-professor.service';
import { HttpSubjectService } from 'src/app/core/services/http-subject.service';

@Component({
  selector: 'app-subject-form',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.css'],
})
export class SubjectFormComponent implements OnInit {
  subject!: Subject;
  subjectId!: number;
  professors?: Professor[];
  subscription = new Subscription();

  constructor(
    private httpSubject: HttpSubjectService,
    private httpProfessor: HttpProfessorService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.subjectId = Number(this.activatedRoute.snapshot.params['subjectId']);
    this.getSubject();
    this.loadProfessors();
  }

  getSubject() {
    this.subscription.add(
      this.httpSubject
        .getById(this.subjectId)
        .subscribe((subject) => (this.subject = subject))
    );
  }

  updateSubject() {
    this.subscription.add(
      this.httpSubject.editSubject(this.subject).subscribe((subject) => {
        this.subject = subject;
        this.router.navigate(['/subjects']);
      })
    );
  }

  loadProfessors() {
    this.subscription.add(
      this.httpProfessor
        .getAll()
        .subscribe((professors) => (this.professors = professors))
    );
  }

  compareFn(professor1: Professor, professor2: Professor): boolean {
    return professor1.id === professor2.id;
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
