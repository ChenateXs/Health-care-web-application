import { Directive, EventEmitter, HostBinding, HostListener, Input, Output } from '@angular/core';

export type SortDirection = '' |'ASC' | 'DESC';

export interface SortEvent{
  column: string;
  direction: SortDirection;

}

@Directive({
  selector: 'th[sortable]',
  host:{
    '(click)':'changeSortDirection()',
    '[class.asc]': 'sortDirection === "ASC"',
    '[class.desc]': 'sortDirection === "DESC"',
  }
})
export class SortableHeaderDirective {

  @Input() sortable: string = '';
  @Input() sortDirection: SortDirection = '';

  @Output() sort = new EventEmitter<SortEvent>();

  constructor() {
  }

  changeSortDirection(){
    switch(this.sortDirection){
      case '': this.sortDirection= 'ASC';
        break;
      case 'ASC': this.sortDirection= 'DESC';
        break;
      case 'DESC': this.sortDirection= 'ASC';
        break;
    }
    this.sort.emit({column: this.sortable, direction: this.sortDirection})
  }
}

