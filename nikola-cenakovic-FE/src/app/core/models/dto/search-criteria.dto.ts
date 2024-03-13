import { SearchOperation } from "../enum/search-operation.enum";

export interface SearchCriteria {
	key:string,
  value: any,
  operation:SearchOperation
}
