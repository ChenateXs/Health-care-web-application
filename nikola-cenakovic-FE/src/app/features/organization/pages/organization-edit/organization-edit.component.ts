import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization, OrganizationType } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpOrganizationTypeService } from 'src/app/core/services/organizationType/http-organization-type.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-organization-edit',
  templateUrl: './organization-edit.component.html',
  styleUrls: ['./organization-edit.component.css']
})
export class OrganizationEditComponent implements OnInit {

  organizationForm?: FormGroup;

  organization!: Organization;

  organizationTypeList!: OrganizationType[];

  constructor(
    private httpOrganizationService:HttpOrganizationService,
    private httpOrganizationTypeService:HttpOrganizationTypeService,
    private activatedRoute:ActivatedRoute,
    private toastService:ToastService,
    private formBuilder:FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
    this.loadOrganization();
    this.loadOrganizationTypeList();

  }

  loadOrganization(){
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.httpOrganizationService.findById(id).subscribe(org =>{
      this.organization = org
      this.createOrganizationForm();
    });
  }

  loadOrganizationTypeList(){
    this.httpOrganizationTypeService.findAll().subscribe(orgTypeList =>
      this.organizationTypeList = orgTypeList);
  }

  createOrganizationForm(){
    this.organizationForm = this.formBuilder.group({
      idOrganization: [this.organization.idOrganization],
      identifier: [this.organization.identifier, [Validators.minLength(5)]],
      organizationType: [this.organization.organizationType, Validators.required],
      name: [this.organization.name, [Validators.required,Validators.minLength(5)]],
      address: [this.organization.address],
      phone: [this.organization.phone],
      email: [this.organization.email,[Validators.pattern(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:"
      + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-"
      + "\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
      + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0"
      + "-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:["
      + "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x"
      + "0b\\x0c\\x0e-\\x7f])+)\\])")]]
    })
  }

  hasErrors(controlName: string, error: string) {
    const control = this.organizationForm?.get(controlName);
    return control && control.hasError(error) && (control.touched || control.dirty)
  }

  editOrganization(){
    const organization = this.organizationForm?.getRawValue();
    this.httpOrganizationService.edit(organization)
    .subscribe(()=>{
      this.router.navigate(['organization']);
      this.toastService.showToast("Organization have been edited.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Invalid data organization not be edited!",
            { className: 'bg-danger text-light' });
    })
  }

  compareOrganizationTypeId(organizationTypeOne: OrganizationType, organizationTypeTwo: OrganizationType) {
    return organizationTypeOne && organizationTypeTwo && organizationTypeOne.idOrganizationType == organizationTypeTwo.idOrganizationType;
  }
}
