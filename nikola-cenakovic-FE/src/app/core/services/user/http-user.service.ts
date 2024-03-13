import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../../models';
import { ToastService } from '../toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class HttpUserService {

  constructor(
    private httpClient: HttpClient,
    private toastService: ToastService) { }

  login( user:User ) {
    return this.httpClient.post<User>(`${environment.serverUrl}/auth/login`, user);
  }
}
