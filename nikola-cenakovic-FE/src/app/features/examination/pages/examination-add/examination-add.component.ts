import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PriorityType, StatusType } from 'src/app/core/models';
import { Examination, Organization, Patient, Practitioner, ServiceType } from 'src/app/core/models';
import { HttpExaminationService } from 'src/app/core/services/examination/http-examination.service';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { HttpServiceTypeService } from 'src/app/core/services/serviceType/http-service-type.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-examination-add',
  templateUrl: './examination-add.component.html',
  styleUrls: ['./examination-add.component.css']
})
export class ExaminationAddComponent implements OnInit {

  examinationForm?: FormGroup;

  examination!: Examination;

  organizationList?: Organization[];
  practitionerList?: Practitioner[];
  patientList?: Patient[];
  serviceTypeList?: ServiceType[];


  constructor(
    private httpExaminationService:HttpExaminationService,
    private httpPractitionerService:HttpPractitionerService,
    private httpOrganizationService:HttpOrganizationService,
    private httpServiceTypeService:HttpServiceTypeService,
    private httpPatientService:HttpPatientService,
    private toastService:ToastService,
    private formBuilder:FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadOrganizationList();
    this.loadPractitionerList();
    this.loadServiceTypeList();
    this.loadPatientList();
    this.createExaminationForm();
  }

  loadPatientList(){
    this.httpPatientService.findAll().subscribe(patList =>
      this.patientList = patList);
  }

  loadOrganizationList(){
    this.httpOrganizationService.findAll().subscribe(orgList =>
      this.organizationList = orgList);
  }

  loadPractitionerList(){
    this.httpPractitionerService.findAll().subscribe(prcList =>
      this.practitionerList = prcList);
  }

  loadServiceTypeList(){
    this.httpServiceTypeService.findAll().subscribe(stList =>
      this.serviceTypeList = stList);
  }

  createExaminationForm(){
    this.examinationForm = this.formBuilder.group({
      idExamination: [-1],
      identifier: [this.examination?.identifier, [Validators.minLength(5)]],
      status: [this.examination?.status, [Validators.required]],
      serviceType: [this.examination?.serviceType, [Validators.required]],
      priority: [this.examination?.priority],
      startDate: [new DatePipe('en-US').transform(this.examination?.startDate, 'yyyy-MM-dd')],
      endDate: [new DatePipe('en-US').transform(this.examination?.endDate, 'yyyy-MM-dd')],
      diagnosis: [this.examination?.diagnosis],
      organization: [this.examination?.organization, [Validators.required]],
      patient: [this.examination?.patient, [Validators.required]],
      practitioners: [this.examination?.practitioners, [Validators.required]],
    },{ validator: [this.compateFormDate('startDate','endDate')]})
  }

  compateFormDate(startDateField: string, endDateField: string, errorName: string = 'fromDate'): ValidatorFn {
    return (formGroup: AbstractControl): { [key: string]: boolean } | null => {
        const startDate = formGroup.get(startDateField)!.value;
        const endDate = formGroup.get(endDateField)!.value;

        if ((startDate !== null && endDate !== null) && startDate > endDate) {
            return {[errorName]: true};
        }
        return null;
    };
}

  hasErrors(controlName: string, error: string) {
    const control = this.examinationForm?.get(controlName);
    return control && control.hasError(error) && (control.touched || control.dirty)
  }

  addExamination(){
    const examination = this.examinationForm?.getRawValue();
    this.httpExaminationService.save(examination)
    .subscribe(()=>{
      this.toastService.showToast("You have successfuly added a examination.",
      { className: 'bg-success text-light' });
      this.router.navigate(['/examination']);
    }
    ,error=>{
      this.toastService.showToast("Invalid data examination not be added!",
      { className: 'bg-danger text-light' });
    });
  }

  get getPriorityType(){
    return PriorityType;
  }

  get getStatusType(){
    return StatusType;
  }
}
