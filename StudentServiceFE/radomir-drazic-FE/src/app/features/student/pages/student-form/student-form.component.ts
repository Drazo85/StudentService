import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';
import { City } from 'src/app/core/models/city';
import { Student } from 'src/app/core/models/student';
import { CityTitleService } from 'src/app/core/services/city-title.service';
import { HttpStudentService } from 'src/app/core/services/http-student.service';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css'],
})
export class StudentFormComponent implements OnInit {
  student!: Student;
  studentId!: number;
  cities?: City[];
  subscription = new Subscription();

  constructor(
    private httpStudent: HttpStudentService,
    private cityTitle: CityTitleService,
    private activedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.studentId = Number(this.activedRoute.snapshot.params['studentId']);
    this.getStudent();
    this.loadCities();
  }

  getStudent() {
    this.subscription.add(
      this.httpStudent
        .getById(this.studentId)
        .subscribe((student) => (this.student = student))
    );
  }

  updateStudent() {
    this.subscription.add(
      this.httpStudent.editStudent(this.student).subscribe((student) => {
        this.student = student;
        this.router.navigate(['/students']);
      })
    );
  }

  loadCities() {
    this.subscription.add(
      this.cityTitle
        .getAllCities()
        .subscribe((cities) => (this.cities = cities))
    );
  }

  compareFn(city1: City, city2: City): boolean {
    return city1.zipCode === city2.zipCode;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
