import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Gender, MaritalStatus, Patient } from 'src/app/core/models';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-patient-bulk-import',
  templateUrl: './patient-bulk-import.component.html',
  styleUrls: ['./patient-bulk-import.component.css']
})
export class PatientBulkImportComponent implements OnInit {

  list: Patient[]= [];
  csvFile!: File;

  constructor(
    private httpOrganizationService: HttpOrganizationService,
    private httpPractitionerService: HttpPractitionerService,
    private httpPatientService: HttpPatientService,
    private toastService: ToastService,
    private router:Router) { }

  ngOnInit(): void {
  }

  uploadFile(){
    this.httpPatientService.bulkImport(this.list).subscribe(()=>{
      this.router.navigate(['patient']);
      this.toastService.showToast("Practitioners have been added.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Imported practitioner information not valid!",
            { className: 'bg-danger text-light' });
    })
  }

  loadFile(event: any) {
    this.loadFileOfPatients(event);
  }

  loadFileOfPatients(event: any) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      let lines: string[] = e.target.result.split(/\r?\n/);
      lines.forEach((element) => {
        if (lines.indexOf(element) != 0) {
          let patientFilds = element.split(',');
          if (patientFilds[10])
            this.httpOrganizationService
              .findByIdentifier(patientFilds[10])
              .subscribe((org) => {
                console.log(patientFilds[11]);
                if (patientFilds[11])
                  this.httpPractitionerService
                    .findByIdentifier(String(patientFilds[11]))
                    .subscribe((prc) => {
                      let patient: Patient = {
                        idPatient: -1,
                        identifier: patientFilds[0],
                        name: patientFilds[1],
                        surname: patientFilds[2],
                        gender: <Gender>patientFilds[3],
                        birthDate: new Date(patientFilds[4]),
                        address: patientFilds[5],
                        phone: patientFilds[6],
                        email: patientFilds[7],
                        deceased: Boolean(patientFilds[8]),
                        maritalStatus: <MaritalStatus>patientFilds[9],
                        organization: org,
                        careProvider: prc,
                      };
                      this.list?.push(patient);
                      console.log(patient);
                      console.log(this.list);
                    });
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

  get getMaritalStatus(){
    return MaritalStatus;
  }
}
