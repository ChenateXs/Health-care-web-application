import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserLoginService } from '../../services/user/user-login.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private userLogin: UserLoginService
    ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token = this.userLogin.token;
    if(token){
      request = request.clone(
        {headers: request.headers.set('Authorization',token)}
      )
    }
    return next.handle(request);
  }
}
