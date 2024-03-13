import { DatePipe } from '@angular/common';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FilterParam, Gender, MaritalStatus, Organization, ParamInputType, Patient, Practitioner } from 'src/app/core/models';
import { SearchCriteria } from 'src/app/core/models/dto/search-criteria.dto';
import { SearchOperation } from 'src/app/core/models/enum/search-operation.enum';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPatientService } from 'src/app/core/services/patient/http-patient.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header/sortable-header.directive';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  filterParams!: FilterParam[];
  patientList!: Patient[];
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
    private httpPatientService:HttpPatientService,
    private httpOrganizationService:HttpOrganizationService,
    private httpPractitionerService:HttpPractitionerService,
    private activeRoute: ActivatedRoute,
    private toastService:ToastService,
    private router:Router) {
      const pageNo: number = this.activeRoute.snapshot.data['pageNo'];
      this.pageNo = pageNo;
      this.loadFilteredPatients();
    }

  ngOnInit(): void {
    this.loadFilterParams();
  }

  loadFilterParams() {
    this.httpOrganizationService
      .findAll()
      .subscribe((orgTypeList) => {
        this.httpPractitionerService
        .findAll()
        .subscribe((prcTypeList) => {
          this.setFilterParams(orgTypeList,prcTypeList);
        });
      });
  }

  setFilterParams(organizationList:Organization[],practitionerList: Practitioner[]) {
    this.filterParams = [
      {
        key: 'identifier', name: 'Identifier', inputType: ParamInputType.TEXT, operation: SearchOperation.LIKE, isChecked: false, value: ''
      },
      {
        key: 'name', name: 'Name', inputType: ParamInputType.TEXT, operation: SearchOperation.LIKE, isChecked: false, value: ''
      },
      {
        key: 'surname', name: 'Surname', inputType: ParamInputType.TEXT, operation: SearchOperation.LIKE, isChecked: false, value: ''
      },
      {
        key: 'gender', name: 'Gender', inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: Object.entries(Gender)
         .map((item,indexOf) => ({ key: indexOf, name: item[1], isChecked: false}))
      },
      {
        key: 'birthDate', name: 'Birth date', inputType: ParamInputType.DATE, operation: SearchOperation.GREATER_THAN_DATE, isChecked: false,
        value: new DatePipe('en-US').transform(new Date(), 'yyyy-MM-dd')
      },
      {
        key: 'deceased', name: 'Deceased',inputType: ParamInputType.BOOLEAN, operation: SearchOperation.EQUAL, isChecked: false, value: false
      },
      {
        key: 'maritalStatus', name: 'Marital status',inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: Object.entries(MaritalStatus)
         .map((item,indexOf) => ({ key: indexOf, name: item[1], isChecked: false}))
      },
      {
        key: 'organization', name: 'Organization', isChecked: false, inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL,
         value: organizationList
           .map((item) => ({ key: item.idOrganization, name: item.name, isChecked: false}))
      },
      {
        key: 'careProvider', name: 'Care provider', isChecked: false, inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL,
         value: practitionerList
           .map((item) => ({ key: item.idPractitioner, name: item.name +' '+ item.surname, isChecked: false}))
      },
      {
        key: 'organization', name: 'No organization', inputType: ParamInputType.NULLABLE, operation: SearchOperation.IS_NULL, isChecked: false, value: null
      },
      {
        key: 'careProvider', name: 'No care provider', inputType: ParamInputType.NULLABLE, operation: SearchOperation.IS_NULL, isChecked: false, value: null
      },
    ];
  }

  loadPatient() {
    this.httpPatientService
      .getByPage(this.pageNo, this.pageSize, this.direction, this.sortBy)
      .subscribe((patientPage) => {
        this.patientList = patientPage.content;
        this.collectionSize = patientPage.totalElements;
        this.pageSize = patientPage.size;
      });
  }

  formSearchCriteriaList(searchCriteriaList:SearchCriteria[]){
    this.searchCriteriaList = searchCriteriaList;
    this.loadFilteredPatients();
    this.toggleShowfilter();
  }

  loadFilteredPatients() {
    this.httpPatientService
      .getByFilteredPage(this.pageNo, this.pageSize, this.direction, this.sortBy, this.searchCriteriaList)
      .subscribe((patientPage) => {
        this.patientList = patientPage.content;
        this.collectionSize = patientPage.totalElements;
        this.pageSize = patientPage.size;
      });
  }

  onPageChange(number: number) {
    if (this.pageNo)
      this.loadFilteredPatients();
  }

  onSort(sortEvent: SortEvent) {
    this.sortBy = sortEvent.column;
    this.direction = sortEvent.direction;
    this.headers?.forEach((header) => {
      if (header.sortable !== sortEvent.column) {
        header.sortDirection = '';
      }
    });
    this.loadFilteredPatients();
  }

  get getGender(){
    return Gender;
  }

  get getMaritalStatus(){
    return MaritalStatus;
  }

  onClickEdit(id: number){
    this.router.navigate(['patient/patient-edit/',id]);
  }
  onClickDetails(id: number){
    this.router.navigate(['patient/patient-details/',id]);
  }
  onClickDelete(id: number){
    this.httpPatientService.delete(id)
    .subscribe((message)=>{
      this.loadFilteredPatients();
      this.toastService.showToast("You have successfuly deleted a patient.",
      { className: 'bg-success text-light' });
    }
    ,error=>{
      this.toastService.showToast("Patient can not be deleted some examination is still running!",
      { className: 'bg-danger text-light' });
    });


  }

  toggleShowfilter(){
    this.filterShow = !this.filterShow;
  }
}
