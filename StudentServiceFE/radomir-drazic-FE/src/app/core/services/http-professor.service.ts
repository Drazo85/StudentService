import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageRequest } from '../models/page-request.dto';
import { PageDto } from '../models/page-response.dto';
import { Professor } from '../models/professor';

@Injectable({
  providedIn: 'root'
})
export class HttpProfessorService {

  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<Professor[]>{
    return this.httpClient.get<Professor[]>(`${environment.serverUrl}/professors`);
  }

  getByPage(pageRequest: PageRequest) {
    const params = new HttpParams()
          .set('pageNo', pageRequest.pageNo-1)
          .set('pageSize',pageRequest.pageSize )
          .set('sortBy', pageRequest.sortBy)
          .set('sortOrder', pageRequest.sortOrder)
    return this.httpClient.get<PageDto<Professor>>(`${environment.serverUrl}/professors/filter`, {params});
    }

  deleteProfessor(professor: Professor) {
    return this.httpClient.delete<string>(`${environment.serverUrl}/professors/${professor.id}`, {responseType: 'text' as 'json'});
  }

  getById(professorId: number) {
    return this.httpClient.get<Professor>(`${environment.serverUrl}/professors/${professorId}`);
  }

  editProfessor(professor: Professor){
    return this.httpClient.put<Professor>(`${environment.serverUrl}/professors/${professor.id}`, professor);
  }

  addProfessor(professor: Professor){
    return this.httpClient.post<Professor>(`${environment.serverUrl}/professors`, professor);
  }
}
