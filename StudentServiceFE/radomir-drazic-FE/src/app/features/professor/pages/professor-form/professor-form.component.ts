import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { City } from 'src/app/core/models/city';
import { Professor } from 'src/app/core/models/professor';
import { Title } from 'src/app/core/models/title';
import { CityTitleService } from 'src/app/core/services/city-title.service';
import { HttpProfessorService } from 'src/app/core/services/http-professor.service';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css'],
})
export class ProfessorFormComponent implements OnInit {
  professor!: Professor;
  professorId!: number;
  cities?: City[];
  titles?: Title[];
  subscription = new Subscription;

  constructor(
    private httpProfessor: HttpProfessorService,
    private cityTitle: CityTitleService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.professorId = Number(this.activatedRoute.snapshot.params['id']);
    this.getProfessor();
    this.loadCities();
    this.loadTitles();
  }

  getProfessor() {
    this.subscription.add(
      this.httpProfessor
        .getById(this.professorId)
        .subscribe((professor) => (this.professor = professor))
    );
  }

  updateProfessor() {
    this.subscription.add(
      this.httpProfessor
        .editProfessor(this.professor)
        .subscribe((professor) => {
          this.professor = professor
          this.router.navigate(['/professors']);
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

  loadTitles() {
    this.subscription.add(
      this.cityTitle
        .getAllTitles()
        .subscribe((titles) => (this.titles = titles))
    );
  }

  compareFnCity(city1: City, city2: City): boolean {
    return city1.zipCode === city2.zipCode;
  }

  compareFnTitle(title1: Title, title2: Title): boolean {
    return title1.titleId === title2.titleId;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
