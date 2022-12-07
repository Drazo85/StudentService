import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { ExamTerm } from 'src/app/core/models/examTerm';
import { Subject } from 'src/app/core/models/subject';
import { HttpExamTermService } from 'src/app/core/services/http-exam-term.service';
import { HttpExamService } from 'src/app/core/services/http-exam.service';
import { HttpSubjectService } from 'src/app/core/services/http-subject.service';

@Component({
  selector: 'app-add-exam',
  templateUrl: './add-exam.component.html',
  styleUrls: ['./add-exam.component.css'],
})
export class AddExamComponent implements OnInit {
  examTerms?: ExamTerm[];
  subjects?: Subject[];
  subscription = new Subscription();

  exam: Exam = {
    examTerm: {
      name: '',
      startDate: new Date(),
      endDate: new Date(),
      isActive: false,
    },
    subject: {
      name: '',
      description: '',
      noOfESP: 0,
      yearOfStudy: 0,
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
    },
    date: new Date(),
  };
  constructor(
    private httpExam: HttpExamService,
    private router: Router,
    public modal: NgbActiveModal,
    private httpExamTerm: HttpExamTermService,
    private httpSubject: HttpSubjectService
  ) {}

  ngOnInit(): void {
    this.loadExamTerms();
    this.loadSubjects();
  }

  onAddExam() {
    this.subscription.add(
      this.httpExam.addExam(this.exam).subscribe((exam) => {
        this.exam = exam;
        this.router.navigate(['/examterms']);
      })
    );
    this.modal.close('OK');
  }

  loadExamTerms() {
    this.subscription.add(
      this.httpExamTerm
        .getAll()
        .subscribe((examTerms) => (this.examTerms = examTerms))
    );
  }

  loadSubjects() {
    this.subscription.add(
      this.httpSubject
        .getAll()
        .subscribe((subjects) => (this.subjects = subjects))
    );
  }

  onCancel() {
    this.modal.close('Cancel');
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
