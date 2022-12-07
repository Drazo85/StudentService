import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { City } from 'src/app/core/models/city';
import { Professor } from 'src/app/core/models/professor';
import { Title } from 'src/app/core/models/title';
import { CityTitleService } from 'src/app/core/services/city-title.service';
import { HttpProfessorService } from 'src/app/core/services/http-professor.service';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrls: ['./add-professor.component.css'],
})
export class AddProfessorComponent implements OnInit {
  cities?: City[];
  titles?: Title[];
  subscription = new Subscription;

  professor: Professor = {
    firstName: '',
    lastName: '',
    email: '',
    address: '',
    city: { zipCode: '', name: '' },
    title: { title: '' },
    phoneNumber: '',
    reelectionDate: new Date(),
  };

  constructor(
    private httpProfessor: HttpProfessorService,
    private router: Router,
    public modal: NgbActiveModal,
    private cityTitle: CityTitleService
  ) {}

  ngOnInit(): void {
    this.loadCities();
    this.loadTitles();
  }

  onAddProfessor() {
    this.subscription.add(
      this.httpProfessor.addProfessor(this.professor).subscribe((professor) => {
        this.professor = professor
        this.router.navigate(['/professors']);
      })
    );
    this.modal.close('OK')
  }

  loadCities() {
    this.subscription.add(
      this.cityTitle
        .getAllCities()
        .subscribe((cities) => (this.cities = cities))
    );
  }

  loadTitles() {
    this.subscription.add(
      this.cityTitle
        .getAllTitles()
        .subscribe((titles) => (this.titles = titles))
    );
  }

  onCancel() {
    this.modal.close('Cancel');
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
