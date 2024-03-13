import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'stringToEnum'
})
export class StringToEnumPipe implements PipeTransform {

  transform(value: any, enumType: any): any {
    const indexOf = Object.keys(enumType).indexOf(value as typeof enumType);
    return Object.values(enumType)[indexOf];
  }

}
