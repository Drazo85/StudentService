import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam';
import { PageRequest } from '../models/page-request.dto';
import { PageDto } from '../models/page-response.dto';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class HttpStudentService {

  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<Student[]>{
    return this.httpClient.get<Student[]>(`${environment.serverUrl}/students`);
  }

  getByPage(pageRequest: PageRequest) {
    const params = new HttpParams()
        .set('pageNo', pageRequest.pageNo-1)
        .set('pageSize',pageRequest.pageSize )
        .set('sortBy', pageRequest.sortBy)
        .set('sortOrder', pageRequest.sortOrder)

    return this.httpClient.get<PageDto<Student>>(`${environment.serverUrl}/students/filter`, {params});
  }

  deleteStudent(student: Student) {
    return this.httpClient.delete<string>(`${environment.serverUrl}/students/${student.studentId}`, {responseType: 'text' as 'json'});
  }

  getById(studentId: number) {
    return this.httpClient.get<Student>(`${environment.serverUrl}/students/${studentId}`);
  }

  editStudent(student: Student){
    return this.httpClient.put<Student>(`${environment.serverUrl}/students/${student.studentId}`, student);
  }

  addStudent(student: Student){
    return this.httpClient.post<Student>(`${environment.serverUrl}/students`, student);
  }

  getPassedStudents(examId: number){
    return this.httpClient.get<Student[]>(`${environment.serverUrl}/students/${examId}/passedstudents`);
  }

  getActiveStudents(examId: number){
    return this.httpClient.get<Student[]>(`${environment.serverUrl}/students/${examId}/activestudents`);
  }
}
