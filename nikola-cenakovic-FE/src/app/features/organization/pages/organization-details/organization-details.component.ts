import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Organization, OrganizationType } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {

  organization?:Organization;

  constructor(
    private httpOrganizationService:HttpOrganizationService,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.loadOrganization();
  }

  loadOrganization(){
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.httpOrganizationService.findById(id).subscribe(org =>
      this.organization = org);
  }

  getName(organizationType: any){
    return organizationType?.name;
  }
}
