import { PriorityType } from "../enum/priority-type.enum";
import { StatusType } from "../enum/status-type.enum";
import { Organization } from "./organization.entity";
import { Patient } from "./patient.entity";
import { Practitioner } from "./practitioner.entity";
import { ServiceType } from "./serviceType.entity";

export interface Examination{
  idExamination:number,
	identifier:string,
	status: StatusType,
  serviceType: ServiceType,
  priority:PriorityType,
	startDate: Date,
  endDate: Date,
	diagnosis: string,
  organization: Organization,
	patient: Patient,
	practitioners: Practitioner[]
}
