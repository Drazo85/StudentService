import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ExamTerm } from '../models/examTerm';
import { PageRequest } from '../models/page-request.dto';
import { PageDto } from '../models/page-response.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExamTermService {
  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<ExamTerm[]>{
    return this.httpClient.get<ExamTerm[]>(`${environment.serverUrl}/examterms`);
  }

  getByPage(pageRequest: PageRequest) {
    const params = new HttpParams()
          .set('pageNo', pageRequest.pageNo-1)
          .set('pageSize',pageRequest.pageSize )
          .set('sortBy', pageRequest.sortBy)
          .set('sortOrder', pageRequest.sortOrder)

    return this.httpClient.get<PageDto<ExamTerm>>(`${environment.serverUrl}/examterms/filter`, {params});
    }


  deleteExamTerm(examTerm: ExamTerm) {
    return this.httpClient.delete<string>(`${environment.serverUrl}/examterms/${examTerm.examTermId}`, {responseType: 'text' as 'json'});
  }

  getById(examTermId: number) {
    return this.httpClient.get<ExamTerm>(`${environment.serverUrl}/examterms/${examTermId}`);
  }

  editExamTerm(examTerm: ExamTerm){
    return this.httpClient.put<ExamTerm>(`${environment.serverUrl}/examterms/${examTerm.examTermId}`, examTerm);
  }

  addExamTerm(examTerm: ExamTerm){
    return this.httpClient.post<ExamTerm>(`${environment.serverUrl}/examterms`, examTerm,  {responseType: 'text' as 'json'});
  }
}
