import { DatePipe } from '@angular/common';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination, FilterParam, Organization, ParamInputType, Patient, Practitioner, PriorityType, ServiceType, StatusType } from 'src/app/core/models';
import { SearchCriteria } from 'src/app/core/models/dto/search-criteria.dto';
import { SearchOperation } from 'src/app/core/models/enum/search-operation.enum';
import { HttpExaminationService } from 'src/app/core/services/examination/http-examination.service';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { HttpServiceTypeService } from 'src/app/core/services/serviceType/http-service-type.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header/sortable-header.directive';

@Component({
  selector: 'app-examination-list',
  templateUrl: './examination-list.component.html',
  styleUrls: ['./examination-list.component.css']
})
export class ExaminationListComponent implements OnInit {

  filterParams!: FilterParam[];
  examinationList!: Examination[];
  searchCriteriaList: SearchCriteria[] = [];

  filterShow: boolean = false;

  pageNo: number;
  pageSize: number = 10;
  direction: string = 'ASC';
  sortBy: string = 'identifier';
  collectionSize: number = 100;

  @ViewChildren(SortableHeaderDirective)
  headers?: QueryList<SortableHeaderDirective>;
  constructor(
    private httpExaminationService:HttpExaminationService,
    private httpPractitionerService:HttpPractitionerService,
    private httpOrganizationService:HttpOrganizationService,
    private httpServiceTypeService:HttpServiceTypeService,
    private httpPatientService:HttpPatientService,
    private toastService:ToastService,
    private activeRoute: ActivatedRoute,
    private router:Router) {
      const pageNo: number = this.activeRoute.snapshot.data['pageNo'];
      this.pageNo = pageNo;
      this.loadFilteredExaminations();
    }

  ngOnInit(): void {
    this.loadFilterParams();
  }

  loadFilterParams() {
    this.httpOrganizationService
      .findAll()
      .subscribe((orgList) => {
        this.httpPractitionerService
        .findAll()
        .subscribe((prcList) => {
          this.httpPatientService
          .findAll()
          .subscribe((patList) => {
            this.httpServiceTypeService
            .findAll()
            .subscribe((srvTypeList) => {
              this.setFilterParams(orgList, prcList, patList,srvTypeList);
          });
          });
        });
      });
  }

  setFilterParams(organizationList:Organization[], practitionerList:Practitioner[], patientList:Patient[], serviceTypeList:ServiceType[]) {
    this.filterParams = [
      {
        key: 'identifier', name: 'Identifier', inputType: ParamInputType.TEXT, operation: SearchOperation.LIKE, isChecked: false, value: ''
      },
      {
        key: 'status', name: 'Status',inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: Object.entries(StatusType)
          .map((item,indexOf) => ({ key: indexOf, name: item[1], isChecked: false}))
      },
      {
        key: 'serviceType', name: '	Service type', inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: serviceTypeList
          .map((item) => ({ key: item.idServiceType, name: item.name, isChecked: false}))
      },
      {
        key: 'priority', name: 'Priority',inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: Object.entries(PriorityType)
          .map((item,indexOf) => ({ key: indexOf, name: item[1], isChecked: false}))
      },
      {
        key: 'startDate', name: 'Start date', inputType: ParamInputType.DATE,operation: SearchOperation.GREATER_THAN_DATE, isChecked: false,
          value: new DatePipe('en-US').transform(new Date(), 'yyyy-MM-dd')
      },
      {
        key: 'endDate', name: 'End date', inputType: ParamInputType.DATE, operation: SearchOperation.GREATER_THAN_DATE, isChecked: false,
          value:  new DatePipe('en-US').transform(new Date(), 'yyyy-MM-dd')
      },
      {
        key: 'patient', name: 'Patient', inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: patientList
          .map((item) => ({ key: item.idPatient, name: item.name + ' ' + item.surname, isChecked: false}))
      },
      {
        key: 'organization', name: 'Organization', inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: organizationList
          .map((item) => ({ key: item.idOrganization, name: item.name, isChecked: false}))
      },
      {
        key: 'practitioners', name: 'Practitioners', inputType: ParamInputType.CHECKBOX, operation: SearchOperation.IN_COLLECTION, isChecked: false,
        value: practitionerList
          .map((item) => ({ key: item.idPractitioner, name: item.name + ' ' + item.surname, isChecked: false}))
      }
    ];
  }

  loadPractitioners() {
    this.httpExaminationService
      .getByPage(this.pageNo, this.pageSize, this.direction, this.sortBy)
      .subscribe((examinationPage) => {
        this.examinationList = examinationPage.content;
        this.collectionSize = examinationPage.totalElements;
        this.pageSize = examinationPage.size;
      });
  }

  formSearchCriteriaList(searchCriteriaList:SearchCriteria[]){
    this.searchCriteriaList = searchCriteriaList;
    this.loadFilteredExaminations();
    this.toggleShowfilter();
  }

  loadFilteredExaminations() {
    this.httpExaminationService
      .getByFilteredPage(this.pageNo, this.pageSize, this.direction, this.sortBy, this.searchCriteriaList)
      .subscribe((examinationPage) => {
        this.examinationList = examinationPage.content;
        this.collectionSize = examinationPage.totalElements;
        this.pageSize = examinationPage.size;
      });
  }

  onPageChange(page: number) {
    if (this.pageNo) this.loadFilteredExaminations();
  }

  onSort(sortEvent: SortEvent) {
    this.sortBy = sortEvent.column;
    this.direction = sortEvent.direction;
    this.headers?.forEach((header) => {
      if (header.sortable !== sortEvent.column) {
        header.sortDirection = '';
      }
    });
    this.loadFilteredExaminations();
  }

  onClickEdit(id: number){
    this.router.navigate(['examination/examination-edit/',id]);
  }
  onClickDetails(id: number){
    this.router.navigate(['examination/examination-details/',id]);
  }
  onClickDelete(id: number){
    this.httpExaminationService.delete(id)
    .subscribe((message)=>{
      this.loadFilteredExaminations();
      this.toastService.showToast("You have successfuly deleted a examination.",
      { className: 'bg-success text-light' });
    }
    ,error=>{
      this.toastService.showToast("Examination can not be deleted!",
      { className: 'bg-danger text-light' });
    });
  }

  get getPriorityType(){
    return PriorityType;
  }

  get getStatusType(){
    return StatusType;
  }

  toggleShowfilter(){
    this.filterShow = !this.filterShow;
  }
}
