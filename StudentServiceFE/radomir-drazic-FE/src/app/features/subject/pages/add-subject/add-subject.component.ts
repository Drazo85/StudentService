import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { Professor } from 'src/app/core/models/professor';
import { Subject } from 'src/app/core/models/subject';
import { HttpProfessorService } from 'src/app/core/services/http-professor.service';
import { HttpSubjectService } from 'src/app/core/services/http-subject.service';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css'],
})
export class AddSubjectComponent implements OnInit {
  professors?: Professor[];
  subscription = new Subscription();

  subject: Subject = {
    name: '',
    description: '',
    noOfESP: 3,
    yearOfStudy: 1,
    semester: '',
    professor: {
      firstName: '',
      lastName: '',
      email: '',
      city: { zipCode: '', name: '' },
      title: { title: '' },
      phoneNumber: '',
      reelectionDate: new Date(),
    },
  };

  constructor(
    private httpSubject: HttpSubjectService,
    private httpProfessor: HttpProfessorService,
    private router: Router,
    public modal: NgbActiveModal
  ) {}

  ngOnInit(): void {
    this.loadProfessors();
  }

  onAddSubject() {
    this.subscription.add(
      this.httpSubject.addSubject(this.subject).subscribe((subject) => {
        this.subject = subject;
        this.modal.close('OK');
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

  onCancel() {
    this.modal.close('CANCEL');
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
