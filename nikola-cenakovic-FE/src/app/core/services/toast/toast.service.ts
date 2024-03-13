import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  toasts: any[] = [];

  constructor() { }

  showToast(message: string , options: any = {}){
    this.toasts.push({message: message, ...options});
  }
}
