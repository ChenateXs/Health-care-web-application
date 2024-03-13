import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organization } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpOrganizationTypeService } from 'src/app/core/services/organizationType/http-organization-type.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-organization-bulk-import',
  templateUrl: './organization-bulk-import.component.html',
  styleUrls: ['./organization-bulk-import.component.css']
})
export class OrganizationBulkImportComponent implements OnInit {

  list: Organization[]= [];
  csvFile!: File;

  constructor(
    private httpOrganizationTypeService: HttpOrganizationTypeService,
    private httpOrganizationService: HttpOrganizationService,
    private toastService: ToastService,
    private router:Router) { }

  ngOnInit(): void {
  }

  uploadFile(){
    this.httpOrganizationService.bulkImport(this.list).subscribe(()=>{
      this.router.navigate(['organization']);
      this.toastService.showToast("Organizations have been added.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Imported organization information not valid!",
            { className: 'bg-danger text-light' });
    })
  }

  loadFile(event: any) {
    this.loadFileOfOrganizations(event);
  }

  loadFileOfOrganizations(event: any) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      let lines: string[] = e.target.result.split(/\r?\n/);
      lines.forEach((element) => {
        if (lines.indexOf(element) != 0) {
          let organizationFilds = element.split(',');
          if (Number(organizationFilds[1]))
            this.httpOrganizationTypeService
              .findById(Number(organizationFilds[1]))
              .subscribe((orgType) => {
                let organization: Organization = {
                  idOrganization: -1,
                  identifier: organizationFilds[0],
                  organizationType: orgType,
                  name: organizationFilds[2],
                  address: organizationFilds[3],
                  phone: organizationFilds[4],
                  email: organizationFilds[5],
                };
                this.list?.push(organization);
                console.log(this.list);
              },err=>{
                console.log("ERROR:",err);
              });
        }
      });
    };
    reader.readAsText(event.target.files[0]);
  }

  remove(i:number){
    this.list?.splice(i,1);
  }
}
