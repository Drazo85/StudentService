import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { City } from 'src/app/core/models/city';
import { Student } from 'src/app/core/models/student';
import { Title } from 'src/app/core/models/title';
import { CityTitleService } from 'src/app/core/services/city-title.service';
import { HttpStudentService } from 'src/app/core/services/http-student.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css'],
})
export class AddStudentComponent implements OnInit {
  cities?: City[];
  subscription = new Subscription;

  student: Student = {
    indexNumber: '',
    indexYear: 2000,
    firstName: '',
    lastName: '',
    email: '',
    address: '',
    city: { zipCode: '', name: '' },
    yearOfStudy: 1,
  };

  constructor(
    private httpStudent: HttpStudentService,
    private router: Router,
    public modal: NgbActiveModal,
    private cityTitle: CityTitleService
  ) {}

  ngOnInit(): void {
    this.loadCities();
  }

  onAddStudent() {
    this.httpStudent.addStudent(this.student).subscribe((student) => {
      this.student = student;
      this.modal.close('OK');
      this.router.navigate(['/students']);
      window.location.reload();
    });
  }

  loadCities() {
    this.subscription.add(
      this.cityTitle
        .getAllCities()
        .subscribe((cities) => (this.cities = cities))
    );
  }

  onCancel() {
    this.modal.close('CANCEL');
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
