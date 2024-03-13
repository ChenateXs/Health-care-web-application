import { Component, OnInit } from '@angular/core';
import { Organization, OrganizationType } from 'src/app/core/models';
import { HttpExaminationService } from 'src/app/core/services/examination/http-examination.service';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpOrganizationTypeService } from 'src/app/core/services/organizationType/http-organization-type.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  organizationCnt:number=0;
  practitionerCnt:number=0;
  patientCnt:number=0;
  examinationCnt:number=0;

  constructor(
    private httpOrganizationService:HttpOrganizationService,
    private httpPractitionerService:HttpPractitionerService,
    private httpPatientService:HttpPatientService,
    private httpExaminationService:HttpExaminationService) { }

  ngOnInit(): void {
    this.httpOrganizationService.count([]).subscribe(cnt=>
      this.organizationCnt = cnt
    );
    this.httpPractitionerService.count([]).subscribe(cnt=>
      this.practitionerCnt = cnt
    );
    this.httpPatientService.count([]).subscribe(cnt=>
      this.patientCnt = cnt
    );
    this.httpExaminationService.count([]).subscribe(cnt=>
      this.examinationCnt = cnt
    );
  }
}
