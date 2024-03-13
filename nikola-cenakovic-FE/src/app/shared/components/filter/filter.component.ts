import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FilterParam, ParamInputType } from 'src/app/core/models';
import { SearchCriteria } from 'src/app/core/models/dto/search-criteria.dto';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  @Input() filterParams?:FilterParam[];
  @Output() sendFilterRequest: EventEmitter<SearchCriteria[]> = new EventEmitter();
  @Output() cancelFilterRequest: EventEmitter<string> = new EventEmitter();
  searchCriteriaList: SearchCriteria[] = [];
  constructor() { }

  ngOnInit(): void {
  }

  get getParamInputType(){
    return ParamInputType;
  }

  cancelFilter(){
    this.cancelFilterRequest.emit('cancel');
  }

  filterRequest(){
    this.searchCriteriaList=[];
    this.filterParams?.forEach((item)=>{
      if(item.isChecked){
        switch(item.inputType){

          case ParamInputType.TEXT:{
            if(item.value)
              this.searchCriteriaList.push({ key: item.key, value: item.value, operation: item.operation});
            break;
          }

          case ParamInputType.NULLABLE:{
            this.searchCriteriaList.push({key: item.key, value: item.value, operation: item.operation });
            break;
          }

          case ParamInputType.BOOLEAN:{
            this.searchCriteriaList.push({key: item.key, value: item.value, operation: item.operation });
            break;
          }

          case ParamInputType.DATE:{
            if(item.value)
              this.searchCriteriaList.push({ key:item.key, value:new Date(item.value), operation: item.operation});
              break;
          }

          case ParamInputType.CHECKBOX:{
            if(Array.isArray(item.value)){
              item.value.forEach((subItem)=>{
                if(subItem.isChecked)
                  this.searchCriteriaList.push({ key:item.key, value:subItem.key, operation: item.operation});
                });
            }
            break;
          }
        }
      }
    })
    this.sendFilterRequest.emit(this.searchCriteriaList);
  }
}

