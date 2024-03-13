import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Examination, PriorityType, StatusType } from 'src/app/core/models';
import { HttpExaminationService } from 'src/app/core/services/examination/http-examination.service';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { HttpServiceTypeService } from 'src/app/core/services/serviceType/http-service-type.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'app-examination-bulk-import',
  templateUrl: './examination-bulk-import.component.html',
  styleUrls: ['./examination-bulk-import.component.css']
})
export class ExaminationBulkImportComponent implements OnInit {

  csvFile!: File;
  list: any[]= [];

  constructor(
    private httpOrganizationService: HttpOrganizationService,
    private httpPractitionerService: HttpPractitionerService,
    private httpPatientService: HttpPatientService,
    private httpServiceTypeService: HttpServiceTypeService,
    private httpExaminationService: HttpExaminationService,
    private toastService: ToastService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  uploadFile(){
    this.httpExaminationService.bulkImport(this.list).subscribe(()=>{
      this.router.navigate(['examination']);
      this.toastService.showToast("Examinations have been added.",
            { className: 'bg-success text-light' });
    },err=>{
      this.toastService.showToast("Imported examination information not valid!",
            { className: 'bg-danger text-light' });
    })
  }

  loadFile(event: any) {
    this.loadFileOfExaminations(event);
  }

  loadFileOfExaminations(event: any) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      let lines: string[] = e.target.result.split(/\r?\n/);
      lines.forEach((element) => {
        if (lines.indexOf(element) != 0) {
          let examinationFilds = element.split(',');
          if (Number(examinationFilds[2]))
            this.httpServiceTypeService
              .findById(Number(examinationFilds[2]))
              .subscribe((serType) => {
                if (examinationFilds[7])
                  this.httpOrganizationService
                    .findByIdentifier(examinationFilds[7])
                    .subscribe((org) => {
                      if (examinationFilds[8])
                        this.httpPatientService
                          .findByIdentifier(examinationFilds[8])
                          .subscribe((pat) => {
                            if (examinationFilds[9]){
                                let practitionerIds: string[] = []
                                let k:number = 9;
                                while(k<examinationFilds.length){
                                  practitionerIds.push(examinationFilds[k]);
                                  k++;
                                }
                                this.httpPractitionerService
                                .findAllByIdentifiers(practitionerIds)
                                .subscribe((prcs) => {
                                  let examination: Examination = {
                                    idExamination: -1,
                                    identifier: examinationFilds[0],
                                    status: <StatusType>examinationFilds[1],
                                    serviceType: serType,
                                    priority: <PriorityType>examinationFilds[3],
                                    startDate: new Date(examinationFilds[4]),
                                    endDate: new Date(examinationFilds[5]),
                                    diagnosis: examinationFilds[6],
                                    organization: org,
                                    patient: pat,
                                    practitioners: prcs
                                  };
                                  this.list?.push(examination);
                                  console.log(examination);
                                  console.log(this.list);
                                });
                              }
                          });
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

  get getStatusType(){
    return StatusType;
  }

  get getPriorityType(){
    return PriorityType;
  }
}
