import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam';
import { PageRequest } from '../models/page-request.dto';
import { PageDto } from '../models/page-response.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExamService {

  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<Exam[]>{
    return this.httpClient.get<Exam[]>(`${environment.serverUrl}/exams`);
  }

  getByPage(pageRequest: PageRequest, examTermId: number) {
    const params = new HttpParams()
          .set('pageNo', pageRequest.pageNo-1)
          .set('pageSize',pageRequest.pageSize )
          .set('sortBy', pageRequest.sortBy)
          .set('sortOrder', pageRequest.sortOrder)
          .set('id', pageRequest.id!);
    return this.httpClient.get<PageDto<Exam>>(`${environment.serverUrl}/exams/filter/${examTermId}`, {params});
    }

  deleteExam(exam: Exam) {
    return this.httpClient.delete<string>(`${environment.serverUrl}/exams/${exam.id}`, {responseType: 'text' as 'json'});
  }

  getById(id: number) {
    return this.httpClient.get<Exam>(`${environment.serverUrl}/exams/${id}`);
  }

  editExam(exam: Exam){
    return this.httpClient.put<Exam>(`${environment.serverUrl}/exams/${exam.id}`, exam);
  }

  addExam(exam: Exam){
    return this.httpClient.post<Exam>(`${environment.serverUrl}/exams`, exam,  {responseType: 'text' as 'json'});
  }

  getActiveExams(examId: number){
    return this.httpClient.get<Exam[]>(`${environment.serverUrl}/exams/${examId}/activeexams`);
  }

  checkExam(exam: Exam, studentId: number){
    return this.httpClient.post<Exam>(`${environment.serverUrl}/students/${studentId}/activeexams`, exam);
  }

  removeExam(examId: number, studentId: number){
    return this.httpClient.put<Exam>(`${environment.serverUrl}/students/${studentId}/activeexams/${examId}`, {responseType: 'text' as 'json'});
  }

  getExamsByYear(studentId: number){
    return this.httpClient.get<Exam[]>(`${environment.serverUrl}/exams/${studentId}/exams`)
  }

  getExamsByProfessor(id: number){
    return this.httpClient.get<Exam[]>(`${environment.serverUrl}/exams/${id}/professors`)
  }
}
