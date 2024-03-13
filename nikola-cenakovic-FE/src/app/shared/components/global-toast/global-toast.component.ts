import { Component, OnInit } from '@angular/core';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-global-toast',
  templateUrl: './global-toast.component.html',
  styleUrls: ['./global-toast.component.css']
})
export class GlobalToastComponent implements OnInit {

  constructor(private toastService:ToastService) { }

  ngOnInit(): void {
  }

  get toasts(){
    return this.toastService.toasts;
  }

  removeToast(i:number){
    this.toasts.splice(i,1);
  }
}
