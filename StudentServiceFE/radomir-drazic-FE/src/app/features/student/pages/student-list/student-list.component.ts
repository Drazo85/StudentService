import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { PageRequest } from 'src/app/core/models/page-request.dto';
import { Student } from 'src/app/core/models/student';
import { HttpStudentService } from 'src/app/core/services/http-student.service';
import { ConfirmDeleteComponent } from 'src/app/shared/components/confirm-delete/confirm-delete.component';
import { AddStudentComponent } from '../add-student/add-student.component';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
})
export class StudentListComponent implements OnInit {
  students!: Student[];
  subscription = new Subscription;
  p: any;
  noPerPage: number = 3;
  filters = {
    firstName: '',
    lastName: ''
  }


  constructor(
    private httpStudent: HttpStudentService,
    private activatedRoute: ActivatedRoute,
    private modalService: NgbModal,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  deleteSelectedStudent(student: Student) {
    this.subscription.add(
      this.httpStudent.deleteStudent(student).subscribe(() => {
        this.students = this.students?.filter(
          (s) => s.studentId! !== student.studentId
        );
        this.router.navigate(['/students']);
      })
    );
  }

  onDeleteStudent(student: Student) {
    const modalRef = this.modalService.open(ConfirmDeleteComponent);
    modalRef.componentInstance.message = 'Do you want to delete Student';
    modalRef.componentInstance.header = student.firstName + student.lastName;
    modalRef.result.then(
      (result) => result === 'OK' && this.deleteSelectedStudent(student)
    );
  }

  onAddStudent() {
    const modalRef = this.modalService.open(AddStudentComponent);
    modalRef.result.then((result) => result === 'OK');
  }



  loadStudents(){
    this.subscription.add(
      this.httpStudent.getAll().subscribe(
        students => this.students = this.filterStudents(students)
      )
    )
  }

  loadStudentsByLastName(){
    this.subscription.add(
      this.httpStudent.getAll().subscribe(
        students => this.students = this.filterStudentsByLastName(students)
      )
    )
  }

  filterStudents(students: Student[]){
    return students.filter((student) => {
      return student.firstName.toLowerCase().includes(this.filters.firstName.toLowerCase());
    }
    )
  }

  filterStudentsByLastName(students: Student[]){
    return students.filter((student) => {
      return student.lastName.toLowerCase().includes(this.filters.lastName.toLowerCase());
    }
    )
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
