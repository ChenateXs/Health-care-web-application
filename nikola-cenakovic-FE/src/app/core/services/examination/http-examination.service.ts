import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Examination, PageDto } from '../../models';
import { SearchCriteria } from '../../models/dto/search-criteria.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpExaminationService {

  constructor(
    private httpClient: HttpClient) { }

  findAll(): Observable<Examination[]>{
    return this.httpClient.get<Examination[]>(`${environment.serverUrl}/examination`);
  }
  getByPage(pageNo: number, pageSize: number, direction: string, sortBy: string): Observable<PageDto<Examination>>{
    let params = new HttpParams();
    params = params.append('pageNo',pageNo-1);
    params = params.append('pageSize',pageSize);
    params = params.append('direction',direction);
    params = params.append('sortBy',sortBy);
    return this.httpClient.get<PageDto<Examination>>(`${environment.serverUrl}/examination/filter`,{ params: params });
  }
  getByFilteredPage(pageNo: number, pageSize: number, direction: string, sortBy: string, searchCriteria: SearchCriteria[]): Observable<PageDto<Examination>>{
    let params = new HttpParams();
    params = params.append('pageNo',pageNo-1);
    params = params.append('pageSize',pageSize);
    params = params.append('direction',direction);
    params = params.append('sortBy',sortBy);
    return this.httpClient.post<PageDto<Examination>>(`${environment.serverUrl}/examination/filter`,searchCriteria ,{ params: params });
  }

  count(searchCriteria: SearchCriteria[]): Observable<number>{
    return this.httpClient.post<number>(`${environment.serverUrl}/examination/count`, searchCriteria);
  }

  findById(id: number): Observable<Examination>{
    return this.httpClient.get<Examination>(`${environment.serverUrl}/examination/${id}`);
  }

  edit(examination: Examination){
    return this.httpClient.put<Examination>(`${environment.serverUrl}/examination/${examination.idExamination}`,examination);
  }

  save(examination: Examination){
    return this.httpClient.post<Examination>(`${environment.serverUrl}/examination`,examination);
  }

  bulkImport(examinations: Examination[]){
    return this.httpClient.post(`${environment.serverUrl}/examination/bulk`,examinations
    , { responseType: 'text'});
  }
  delete(id: number){
    return this.httpClient.delete(`${environment.serverUrl}/examination/${id}`
    , { responseType: 'text'});
  }
}
