import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Examination, PriorityType, StatusType } from 'src/app/core/models';
import { HttpExaminationService } from 'src/app/core/services/examination/http-examination.service';

@Component({
  selector: 'app-examination-details',
  templateUrl: './examination-details.component.html',
  styleUrls: ['./examination-details.component.css']
})
export class ExaminationDetailsComponent implements OnInit {

  examination?: Examination;

  constructor(
    private httpExaminationService:HttpExaminationService,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.loadPractitioner();
  }

  loadPractitioner(){
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.httpExaminationService.findById(id).subscribe(exa =>
      this.examination = exa);
  }

  get getPriorityType(){
    return PriorityType;
  }

  get getStatusType(){
    return StatusType;
  }

}
