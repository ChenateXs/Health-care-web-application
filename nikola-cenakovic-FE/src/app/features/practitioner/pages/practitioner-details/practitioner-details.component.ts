import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Practitioner, Gender, Qualification  } from 'src/app/core/models';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';

@Component({
  selector: 'app-practitioner-details',
  templateUrl: './practitioner-details.component.html',
  styleUrls: ['./practitioner-details.component.css']
})
export class PractitionerDetailsComponent implements OnInit {

  practitioner?: Practitioner;

  constructor(
    private httpPractitionerService:HttpPractitionerService,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loadPractitioner();
  }

  loadPractitioner(){
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.httpPractitionerService.findById(id).subscribe(prc =>
      this.practitioner = prc);
  }


  get getGender(){
    return Gender;
  }

  get getQualification(){
    return Qualification;
  }
}
