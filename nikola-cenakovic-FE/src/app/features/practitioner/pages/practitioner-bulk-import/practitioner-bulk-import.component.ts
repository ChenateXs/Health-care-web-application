import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Gender, Practitioner, Qualification } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-practitioner-bulk-import',
  templateUrl: './practitioner-bulk-import.component.html',
  styleUrls: ['./practitioner-bulk-import.component.css']
})
export class PractitionerBulkImportComponent implements OnInit {

  list: Practitioner[]= [];
  csvFile!: File;

  constructor(
    private httpOrganizationService: HttpOrganizationService,
    private httpPractitionerService: HttpPractitionerService,
    private toastService: ToastService,
    private router:Router) { }

  ngOnInit(): void {
  }

  uploadFile(){
    this.httpPractitionerService.bulkImport(this.list).subscribe(()=>{
      this.router.navigate(['practitioner']);
      this.toastService.showToast("Practitioners have been added.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Imported practitioner information not valid!",
            { className: 'bg-danger text-light' });
    })
  }

  loadFile(event: any) {
    this.loadFileOfPractitioners(event);
  }

  loadFileOfPractitioners(event: any) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      let lines: string[] = e.target.result.split(/\r?\n/);
      lines.forEach((element) => {
        if (lines.indexOf(element) != 0) {
          let practitionerFilds = element.split(',');
          if (practitionerFilds[9])
            this.httpOrganizationService
              .findByIdentifier(practitionerFilds[9])
              .subscribe((org) => {
                let practitioner: Practitioner = {
                  idPractitioner: -1,
                  identifier: practitionerFilds[0],
                  name: practitionerFilds[1],
                  surname: practitionerFilds[2],
                  gender: <Gender>practitionerFilds[3],
                  birthDate: new Date(practitionerFilds[4]),
                  address: practitionerFilds[5],
                  phone: practitionerFilds[6],
                  email: practitionerFilds[7],
                  qualification: <Qualification>practitionerFilds[8],
                  organization: org,
                };
                console.log(practitioner);
                this.list?.push(practitioner);
              });
        }
      });
    };
    reader.readAsText(event.target.files[0]);
  }

  remove(i:number){
    this.list?.splice(i,1);
  }

  get getGender(){
    return Gender;
  }

  get getQualification(){
    return Qualification;
  }

}
