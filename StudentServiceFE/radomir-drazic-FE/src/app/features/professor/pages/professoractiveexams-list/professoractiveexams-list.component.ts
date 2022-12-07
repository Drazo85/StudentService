import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Exam } from 'src/app/core/models/exam';
import { HttpExamService } from 'src/app/core/services/http-exam.service';

@Component({
  selector: 'app-professoractiveexams-list',
  templateUrl: './professoractiveexams-list.component.html',
  styleUrls: ['./professoractiveexams-list.component.css']
})
export class ProfessoractiveexamsListComponent implements OnInit {

  id!: number;
  exams!: Exam[];
  subscription = new Subscription;

  constructor( private activatedRoute: ActivatedRoute,
     private httpExam: HttpExamService) { }

  ngOnInit(): void {
    this.id = Number(this.activatedRoute.parent?.snapshot.params['id']);
    this.loadExams();
  }

  loadExams(){
    this.subscription.add(
      this.httpExam.getExamsByProfessor(this.id)
      .subscribe(
        exams => this.exams = exams
      )
    )
  }

}
