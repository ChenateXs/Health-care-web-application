import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'namingManager'
})
export class NamingManagerPipe implements PipeTransform {

  transform(value: any, keys: string[], nullValue: string): any {
    if(value == null)return nullValue;
    else{
      let indexOf:number = 0;
      let name:string = '';
      keys.forEach((key)=>{
        indexOf = Object.keys(value).indexOf(key as typeof value[0]);

        name += Object.values(value)[indexOf] + ' ';
      });
      return name;
    }
  }

}
