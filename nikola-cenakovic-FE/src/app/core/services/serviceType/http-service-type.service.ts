import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServiceType } from '../../models';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceTypeService {

  constructor(
    private httpClient: HttpClient) { }

  findAll(): Observable<ServiceType[]>{
    return this.httpClient.get<ServiceType[]>(`${environment.serverUrl}/service-type`);
  }

  findById(id: number): Observable<ServiceType>{
    return this.httpClient.get<ServiceType>(`${environment.serverUrl}/service-type/${id}`);
  }
}
