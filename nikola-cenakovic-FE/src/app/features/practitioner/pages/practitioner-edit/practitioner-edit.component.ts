import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization, Practitioner, Gender, Qualification  } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { DatePipe } from '@angular/common';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-practitioner-edit',
  templateUrl: './practitioner-edit.component.html',
  styleUrls: ['./practitioner-edit.component.css']
})
export class PractitionerEditComponent implements OnInit {

  practitionerForm?: FormGroup;

  practitioner!: Practitioner;

  organizationList?: Organization[];


  constructor(
    private httpPractitionerService:HttpPractitionerService,
    private httpOrganizationService:HttpOrganizationService,
    private activatedRoute:ActivatedRoute,
    private toastService:ToastService,
    private formBuilder:FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadOrganizationList();
    this.loadPractitioner();
  }

  loadPractitioner(){
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.httpPractitionerService.findById(id).subscribe(org =>{
      this.practitioner = org;
      this.createPractitionerForm();
    });
  }

  loadOrganizationList(){
    this.httpOrganizationService.findAll().subscribe(orgList =>
      this.organizationList = orgList);
  }
  createPractitionerForm(){
    this.practitionerForm = this.formBuilder.group({
      idPractitioner: [this.practitioner.idPractitioner],
      identifier: [this.practitioner.identifier, [Validators.minLength(5)]],
      name: [this.practitioner.name, [Validators.required,Validators.minLength(3)]],
      surname: [this.practitioner.surname, [Validators.required,Validators.minLength(3)]],
      gender: [this.practitioner.gender],
      birthDate: [new DatePipe('en-US').transform(this.practitioner.birthDate, 'yyyy-MM-dd'), [Validators.required]],
      address: [this.practitioner.address],
      phone: [this.practitioner.phone],
      email: [this.practitioner.email,[Validators.pattern(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:"
      + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-"
      + "\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
      + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0"
      + "-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:["
      + "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x"
      + "0b\\x0c\\x0e-\\x7f])+)\\])")]],
      qualification: [this.practitioner.qualification, [Validators.required]],
      organization: [this.practitioner.organization]
    })
  }

  hasErrors(controlName: string, error: string) {
    const control = this.practitionerForm?.get(controlName);
    return control && control.hasError(error) && (control.touched || control.dirty)
  }

  editPractitioner(){
    const practitioner = this.practitionerForm?.getRawValue();
    this.httpPractitionerService.edit(practitioner)
    .subscribe(()=>{
      this.router.navigate(['practitioner']);
      this.toastService.showToast("Practitioner have been edited.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Invalid data practitioner not be edited!",
            { className: 'bg-danger text-light' });
    })
  }

  get getGender(){
    return Gender;
  }

  get getQualification(){
    return Qualification;
  }

  compareOrganizationId(organizationOne: Organization, organizationTwo: Organization) {
    return organizationOne && organizationTwo && organizationOne.idOrganization == organizationTwo.idOrganization;
  }
}
