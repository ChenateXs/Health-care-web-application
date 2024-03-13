import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageDto, Patient } from '../../models';
import { SearchCriteria } from '../../models/dto/search-criteria.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpPatientService {

  constructor(
    private httpClient: HttpClient) { }

    findAll(): Observable<Patient[]>{
      return this.httpClient.get<Patient[]>(`${environment.serverUrl}/patient`);
    }
    getByPage(pageNo: number, pageSize: number, direction: string, sortBy: string): Observable<PageDto<Patient>>{
      let params = new HttpParams();
      params = params.append('pageNo',pageNo-1);
      params = params.append('pageSize',pageSize);
      params = params.append('direction',direction);
      params = params.append('sortBy',sortBy);
      return this.httpClient.get<PageDto<Patient>>(`${environment.serverUrl}/patient/filter`,{ params: params });
    }
    getByFilteredPage(pageNo: number, pageSize: number, direction: string, sortBy: string, searchCriteria: SearchCriteria[]): Observable<PageDto<Patient>>{
      let params = new HttpParams();
      params = params.append('pageNo',pageNo-1);
      params = params.append('pageSize',pageSize);
      params = params.append('direction',direction);
      params = params.append('sortBy',sortBy);
      return this.httpClient.post<PageDto<Patient>>(`${environment.serverUrl}/patient/filter`,searchCriteria ,{ params: params });
    }

    count(searchCriteria: SearchCriteria[]): Observable<number>{
      return this.httpClient.post<number>(`${environment.serverUrl}/patient/count`, searchCriteria);
    }

    findById(id: number): Observable<Patient>{
      return this.httpClient.get<Patient>(`${environment.serverUrl}/patient/${id}`);
    }

    findByIdentifier(identifier: string): Observable<Patient>{
      return this.httpClient.get<Patient>(`${environment.serverUrl}/patient/identifier/${identifier}`);
    }

    edit(patient: Patient){
      return this.httpClient.put<Patient>(`${environment.serverUrl}/patient/${patient.idPatient}`,patient);
    }

    save(patient: Patient){
      console.log(patient);
      return this.httpClient.post<Patient>(`${environment.serverUrl}/patient`,patient);
    }

    bulkImport(practitioners: Patient[]){
      return this.httpClient.post(`${environment.serverUrl}/patient/bulk`,practitioners
      , { responseType: 'text'});
    }

    delete(id: number){
      return this.httpClient.delete(`${environment.serverUrl}/patient/${id}`, { responseType: 'text'});
    }
}
