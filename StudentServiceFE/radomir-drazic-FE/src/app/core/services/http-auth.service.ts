import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginData } from '../models/user-login-data.model';


@Injectable({
  providedIn: 'root'
})
export class HttpAuthService {

  constructor(private httpClient: HttpClient) { }

  login(userLogin: {username: string, password: string}){
    const params = new URLSearchParams();
    params.set("username", userLogin.username);
    params.set("password", userLogin.password);

    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/x-www-form-urlencoded');

    return this.httpClient.post<UserLoginData>('http://localhost:8095/auth/login', params, {headers});
  }
}
