import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { City } from '../models/city';
import { Title } from '../models/title';

@Injectable({
  providedIn: 'root'
})
export class CityTitleService {

  constructor(private httpClient: HttpClient) { }

  getAllCities() : Observable<City[]>{
    return this.httpClient.get<City[]>(`${environment.serverUrl}/cities`);
  }

  getAllTitles() : Observable<Title[]>{
    return this.httpClient.get<Title[]>(`${environment.serverUrl}/titles`);
  }
}
