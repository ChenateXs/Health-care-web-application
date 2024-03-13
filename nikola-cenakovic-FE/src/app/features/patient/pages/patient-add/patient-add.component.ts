import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Organization, Patient, Practitioner, Gender, MaritalStatus } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {
  patientForm?: FormGroup;

  patient?: Patient;

  organizationList?: Organization[];
  practitionerList?: Practitioner[];
  practitionerListFromOrganization?: Practitioner[] = [];


  constructor(
    private httpPatientService:HttpPatientService,
    private httpPractitionerService:HttpPractitionerService,
    private httpOrganizationService:HttpOrganizationService,
    private toastService:ToastService,
    private formBuilder:FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadOrganizationList();
    this.loadPractitionerList();
    this.createPatientForm();
  }

  loadPractitionerListFromOrganization(){
    console.log(this.patientForm?.getRawValue().organization.idOrganization);
    this.practitionerListFromOrganization = this.practitionerList?.filter(item=>item.organization.idOrganization===this.patientForm?.getRawValue().organization.idOrganization);
  }

  loadPractitionerList(){
    this.httpPractitionerService.findAllDoctorOfMadicine().subscribe(prcList =>
      this.practitionerList = prcList);
  }
  loadOrganizationList(){
    this.httpOrganizationService.findAll().subscribe(orgList =>
      this.organizationList = orgList);
  }
  createPatientForm(){
    this.patientForm = this.formBuilder.group({
      idPatient: [-1],
      identifier: [this.patient?.identifier, [Validators.minLength(5)]],
      name: [this.patient?.name, [Validators.required,Validators.minLength(3)]],
      surname: [this.patient?.surname, [Validators.required,Validators.minLength(3)]],
      gender: [this.patient?.gender],
      birthDate: [new DatePipe('en-US').transform(this.patient?.birthDate, 'yyyy-MM-dd'), [Validators.required]],
      address: [this.patient?.address],
      phone: [this.patient?.phone],
      email: [this.patient?.email,[Validators.pattern(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:"
      + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-"
      + "\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
      + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0"
      + "-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:["
      + "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x"
      + "0b\\x0c\\x0e-\\x7f])+)\\])")]],
      deceased: [this.patient?.deceased],
      maritalStatus: [this.patient?.maritalStatus],
      careProvider: [this.patient?.careProvider],
      organization: [this.patient?.organization]
    })
  }

  hasErrors(controlName: string, error: string) {
    const control = this.patientForm?.get(controlName);
    return control && control.hasError(error) && (control.touched || control.dirty)
  }

  addPatient(){
    const patient = this.patientForm?.getRawValue();
    console.log(patient);
    this.httpPatientService.save(patient)
    .subscribe(()=>{
      this.router.navigate(['patient']);
      this.toastService.showToast("Patient have been added.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Invalid data patient not be added!",
            { className: 'bg-danger text-light' });
    })

  }

  get getGender(){
    return Gender;
  }

  get getMaritalStatus(){
    return MaritalStatus;
  }
}
