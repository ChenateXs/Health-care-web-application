import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { User } from '../../models';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {
  userData?:{username:string}

  userDataSubject: Subject<{username: string} | null> = new Subject();

  constructor() { }

  setLoginCredentials(user:User){
    this.storage.setItem('token', 'Basic ' + btoa(`${user.username}:${user.password}`));
    this.userData = {
      username: user.username
    }
    this.userDataSubject.next(this.userData);
  }

  logoutUser(){
    this.storage.removeItem('token');
    this.userData = undefined;
    this.userDataSubject.next(null);
  }

  get storage(){
    return localStorage;
  }

  get token(){
    return this.storage.getItem('token');
  }

  get isUserLoggedIn(){
    return !!this.token;
  }
}
