
import { Gender } from "../enum/gender.enum";
import { MaritalStatus } from "../enum/marital-status.enum";
import { Organization } from "./organization.entity";
import { Practitioner } from "./practitioner.entity";

export interface Patient{
  idPatient: number,
  identifier: string,
  name: string,
  surname: string,
  gender: Gender,
  birthDate: Date,
  address: string,
  phone: string,
  email: string,
  deceased: boolean,
  maritalStatus: MaritalStatus,
  organization: Organization,
  careProvider: Practitioner
}
