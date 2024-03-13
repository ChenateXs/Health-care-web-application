import { ParamInputType } from "../enum/param-input-type.enum";
import { SearchOperation } from "../enum/search-operation.enum";

export interface FilterParam{
  key:string;
  name:string;
  inputType:ParamInputType;
  operation:SearchOperation;
  isChecked:boolean;
  value:any;
}
