import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageDto, Practitioner } from '../../models';
import { SearchCriteria } from '../../models/dto/search-criteria.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpPractitionerService {

  constructor(
    private httpClient: HttpClient) { }

    findAll(): Observable<Practitioner[]>{
      return this.httpClient.get<Practitioner[]>(`${environment.serverUrl}/practitioner`);
    }

    findAllDoctorOfMadicine(): Observable<Practitioner[]>{
      return this.httpClient.get<Practitioner[]>(`${environment.serverUrl}/practitioner/doctors_of_madicine`);
    }

    findAllByIdentifiers(practitionerIds: string[]): Observable<Practitioner[]>{
      return this.httpClient.post<Practitioner[]>(`${environment.serverUrl}/practitioner/getByIdentifiers`, practitionerIds);
    }

    getByPage(pageNo: number, pageSize: number, direction: string, sortBy: string): Observable<PageDto<Practitioner>>{
      let params = new HttpParams();
      params = params.append('pageNo',pageNo-1);
      params = params.append('pageSize',pageSize);
      params = params.append('direction',direction);
      params = params.append('sortBy',sortBy);
      return this.httpClient.get<PageDto<Practitioner>>(`${environment.serverUrl}/practitioner/filter`,{ params: params });
    }

    getByFilteredPage(pageNo: number, pageSize: number, direction: string, sortBy: string, searchCriteria: SearchCriteria[]): Observable<PageDto<Practitioner>>{
      let params = new HttpParams();
      params = params.append('pageNo',pageNo-1);
      params = params.append('pageSize',pageSize);
      params = params.append('direction',direction);
      params = params.append('sortBy',sortBy);
      return this.httpClient.post<PageDto<Practitioner>>(`${environment.serverUrl}/practitioner/filter`,searchCriteria ,{ params: params });
    }

    count(searchCriteria: SearchCriteria[]): Observable<number>{
      return this.httpClient.post<number>(`${environment.serverUrl}/practitioner/count`, searchCriteria);
    }

    findById(id: number): Observable<Practitioner>{
      return this.httpClient.get<Practitioner>(`${environment.serverUrl}/practitioner/${id}`);
    }

    findByIdentifier(identifier: string): Observable<Practitioner>{
      return this.httpClient.get<Practitioner>(`${environment.serverUrl}/practitioner/identifier/${identifier}`);
    }

    edit(practitioner: Practitioner){
      return this.httpClient.put<Practitioner>(`${environment.serverUrl}/practitioner/${practitioner.idPractitioner}`,practitioner);
    }

    save(practitioner: Practitioner){
      return this.httpClient.post<Practitioner>(`${environment.serverUrl}/practitioner`,practitioner);
    }

    bulkImport(practitioners: Practitioner[]){
      return this.httpClient.post(`${environment.serverUrl}/practitioner/bulk`,practitioners
      , { responseType: 'text'});
    }

    delete(id: number){
      return this.httpClient.delete(`${environment.serverUrl}/practitioner/${id}`, { responseType: 'text'});
    }
}
