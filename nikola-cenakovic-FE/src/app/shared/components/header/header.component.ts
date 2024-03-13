import { Component, OnInit } from '@angular/core';
import { UserLoginService } from 'src/app/core/services/user/user-login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private loginService:UserLoginService) { }

  ngOnInit(): void {
  }

  logout(){
    this.loginService.logoutUser();
  }

  get loggedIn(){
    return !!this.loginService.isUserLoggedIn;
  }
}
