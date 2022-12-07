import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { PassedExam } from 'src/app/core/models/passedexam';
import { Student } from 'src/app/core/models/student';
import { HttpPassedexamService } from 'src/app/core/services/http-passedexam.service';
import { ConfirmDeleteComponent } from 'src/app/shared/components/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-passedexams-list',
  templateUrl: './passedexams-list.component.html',
  styleUrls: ['./passedexams-list.component.css'],
})
export class PassedexamsListComponent implements OnInit {
  studentId!: number;
  exams!: PassedExam[];
  students!: Student[];
  subscription = new Subscription;

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpPassedExam: HttpPassedexamService,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.studentId = Number(
      this.activatedRoute.parent?.snapshot.params['studentId']
    );
    this.loadExams();
  }

  loadExams() {
    this.subscription.add(
      this.httpPassedExam
        .getAll()
        .subscribe(
          (exams) =>
            (this.exams = exams.filter(
              (exam) => this.studentId == exam.student!.studentId
            ))
        )
    );
  }

  deletePassedExam(passedExam: PassedExam) {
    this.subscription.add(
      this.httpPassedExam.deleteExam(passedExam).subscribe(() => {
        this.exams = this.exams?.filter(
          (p) => p.passedExamId! !== passedExam.passedExamId
        );
        this.router.navigate(['/students']);
      })
    );
  }

  onDeletePassedExam(paseedExam: PassedExam) {
    const modalRef = this.modalService.open(ConfirmDeleteComponent);
    modalRef.componentInstance.message = 'Do you want to delete Passed exam?';
    modalRef.componentInstance.header = paseedExam.exam?.subject.name;
    modalRef.result.then(
      (result) => result === 'OK' && this.deletePassedExam(paseedExam)
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
