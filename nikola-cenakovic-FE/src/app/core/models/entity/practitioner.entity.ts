
import { Gender } from "../enum/gender.enum";
import { Qualification } from "../enum/qualification.enum";
import { Organization } from "./organization.entity";

export interface Practitioner{
	idPractitioner:number;
	identifier: string;
	name: string;
	surname: string;
	gender: Gender;
  birthDate: Date;
	address: string;
	phone: string;
	email: string;
	qualification : Qualification;
	organization: Organization;
}
