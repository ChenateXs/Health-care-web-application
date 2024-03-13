import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Organization, PageDto } from '../../models';
import { SearchCriteria } from '../../models/dto/search-criteria.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpOrganizationService {

  constructor(
    private httpClient: HttpClient) { }

  findAll(): Observable<Organization[]>{
    return this.httpClient.get<Organization[]>(`${environment.serverUrl}/organization`);
  }

  getByPage(pageNo: number, pageSize: number, direction: string, sortBy: string): Observable<PageDto<Organization>>{
    let params = new HttpParams();
    params = params.append('pageNo',pageNo-1);
    params = params.append('pageSize',pageSize);
    params = params.append('direction',direction);
    params = params.append('sortBy',sortBy);
    return this.httpClient.get<PageDto<Organization>>(`${environment.serverUrl}/organization/filter`,{ params: params });
  }
  getByFilteredPage(pageNo: number, pageSize: number, direction: string, sortBy: string, searchCriteria: SearchCriteria[]): Observable<PageDto<Organization>>{
    let params = new HttpParams();
    params = params.append('pageNo',pageNo-1);
    params = params.append('pageSize',pageSize);
    params = params.append('direction',direction);
    params = params.append('sortBy',sortBy);
    return this.httpClient.post<PageDto<Organization>>(`${environment.serverUrl}/organization/filter`,searchCriteria ,{ params: params });
  }

  count(searchCriteria: SearchCriteria[]): Observable<number>{
    return this.httpClient.post<number>(`${environment.serverUrl}/organization/count`, searchCriteria);
  }

  findById(id: number): Observable<Organization>{
    return this.httpClient.get<Organization>(`${environment.serverUrl}/organization/${id}`);
  }

  findByIdentifier(identifier: string): Observable<Organization>{
    return this.httpClient.get<Organization>(`${environment.serverUrl}/organization/identifier/${identifier}`);
  }

  edit(organization: Organization){
    return this.httpClient.put<Organization>(`${environment.serverUrl}/organization/${organization.idOrganization}`,organization);
  }

  save(organization: Organization){
    return this.httpClient.post<Organization>(`${environment.serverUrl}/organization`,organization);
  }

  bulkImport(organizations: Organization[]){
    return this.httpClient.post(`${environment.serverUrl}/organization/bulk`,organizations
    , { responseType: 'text'});
  }

  delete(id: number){
    return this.httpClient.delete(`${environment.serverUrl}/organization/${id}`
    , { responseType: 'text'});
  }
}
