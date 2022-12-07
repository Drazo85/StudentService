import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageRequest } from '../models/page-request.dto';
import { PageDto } from '../models/page-response.dto';
import { Subject } from '../models/subject';

@Injectable({
  providedIn: 'root'
})
export class HttpSubjectService {

  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<Subject[]>{
    return this.httpClient.get<Subject[]>(`${environment.serverUrl}/subjects`);
  }

  getByPage(pageRequest: PageRequest) {
    const params = new HttpParams()
          .set('pageNo', pageRequest.pageNo-1)
          .set('pageSize',pageRequest.pageSize )
          .set('sortBy', pageRequest.sortBy)
          .set('sortOrder', pageRequest.sortOrder)

    return this.httpClient.get<PageDto<Subject>>(`${environment.serverUrl}/subjects/filter`, {params});
    }

  deleteSubject(subject: Subject) {
    return this.httpClient.delete<string>(`${environment.serverUrl}/subjects/${subject.subjectId}`, {responseType: 'text' as 'json'});
  }

  getById(subjectId: number) {
    return this.httpClient.get<Subject>(`${environment.serverUrl}/subjects/${subjectId}`);
  }

  editSubject(subject: Subject){
    return this.httpClient.put<Subject>(`${environment.serverUrl}/subjects/${subject.subjectId}`, subject);
  }

  addSubject(subject: Subject){
    return this.httpClient.post<Subject>(`${environment.serverUrl}/subjects`, subject);
  }
}
