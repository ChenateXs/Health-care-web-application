import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, throwError } from 'rxjs';
import { User } from 'src/app/core/models';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { HttpUserService } from 'src/app/core/services/user/http-user.service';
import { UserLoginService } from 'src/app/core/services/user/user-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private httpUser:HttpUserService,
    private router:Router,
    private toastService: ToastService,
    private userLogin: UserLoginService) { }

  loginForm?: FormGroup;

  subscription: Subscription = new Subscription();

  ngOnInit(): void {
    this.createForm();
  }

  createForm(){
    this.loginForm = this.formBuilder.group({
      username: ['',[Validators.required]],
      password: ['',[Validators.required]]
    })
  }

  login(){
    this.subscription.add(
      this.httpUser.login(this.loginForm?.value)
      .subscribe(
        (user: User) => {
            console.log('userDetails:',user);
            this.userLogin.setLoginCredentials(user);
            this.toastService.showToast("You have successfuly logged in",
            { header: 'Login', className: 'bg-success text-light' });
            this.router.navigate(['/home']);
          },error =>{

            console.error(error);

            this.toastService.showToast("Username or password is incorect, try again",
            { header: 'Login', className: 'bg-danger text-light' });

            return throwError(error);
          }
        )
    );
  }
}
