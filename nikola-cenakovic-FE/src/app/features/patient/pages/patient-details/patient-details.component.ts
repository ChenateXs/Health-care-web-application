import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Patient, Gender, MaritalStatus } from 'src/app/core/models';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {
  patient?: Patient;
  constructor(
    private httpPatientService:HttpPatientService,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.loadPatient();
  }

  loadPatient(){
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.httpPatientService.findById(id).subscribe(pat =>
      this.patient = pat);
  }


  get getGender(){
    return Gender;
  }

  get getMaritalStatus(){
    return MaritalStatus;
  }
}
