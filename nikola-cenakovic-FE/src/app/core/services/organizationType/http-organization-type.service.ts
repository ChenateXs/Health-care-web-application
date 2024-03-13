import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrganizationType } from '../../models';

@Injectable({
  providedIn: 'root'
})
export class HttpOrganizationTypeService {

  constructor(
    private httpClient: HttpClient) { }

  findAll(): Observable<OrganizationType[]>{
    return this.httpClient.get<OrganizationType[]>(`${environment.serverUrl}/organization-type`);
  }

  findById(id: number): Observable<OrganizationType>{
    return this.httpClient.get<OrganizationType>(`${environment.serverUrl}/organization-type/${id}`);
  }
}
