import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PassedExam } from '../models/passedexam';

@Injectable({
  providedIn: 'root'
})
export class HttpPassedexamService {

  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<PassedExam[]>{
    return this.httpClient.get<PassedExam[]>(`${environment.serverUrl}/passedexams`);
  }

  deleteExam(exam: PassedExam) {
    return this.httpClient.delete<string>(`${environment.serverUrl}/passedexams/${exam.passedExamId}`, {responseType: 'text' as 'json'});
  }

  getById(id: number) {
    return this.httpClient.get<PassedExam>(`${environment.serverUrl}/passedexams/${id}`);
  }

  editExam(exam: PassedExam){
    return this.httpClient.put<PassedExam>(`${environment.serverUrl}/passedexams/`, exam);
  }

  addExam(exam: PassedExam){
    return this.httpClient.post<PassedExam>(`${environment.serverUrl}/passedexams`, exam,  {responseType: 'text' as 'json'});
  }
}
