import { OrganizationType } from "./organizationType.entity";

export interface Organization{
  idOrganization: number;
	identifier: string;
	organizationType: OrganizationType;
	name:string;
	address: string;
	phone:string;
  email:string;
}

