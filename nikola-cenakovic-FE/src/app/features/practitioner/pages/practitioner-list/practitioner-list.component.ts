import { DatePipe } from '@angular/common';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Gender, Qualification, Practitioner, FilterParam, Organization, ParamInputType } from 'src/app/core/models';
import { SearchCriteria } from 'src/app/core/models/dto/search-criteria.dto';
import { SearchOperation } from 'src/app/core/models/enum/search-operation.enum';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpPractitionerService } from 'src/app/core/services/practitioner/http-practitioner.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header/sortable-header.directive';

@Component({
  selector: 'app-practitioner-list',
  templateUrl: './practitioner-list.component.html',
  styleUrls: ['./practitioner-list.component.css']
})
export class PractitionerListComponent implements OnInit {


  filterParams!: FilterParam[];
  practitionerList!: Practitioner[];
  searchCriteriaList: SearchCriteria[] = [];

  filterShow: boolean = false;

  pageNo: number = 1;
  pageSize: number = 10;
  direction: string = 'ASC';
  sortBy: string = 'identifier';
  collectionSize: number = 100;

  @ViewChildren(SortableHeaderDirective)
  headers?: QueryList<SortableHeaderDirective>;

  constructor(
    private httpPractitionerService:HttpPractitionerService,
    private httpOrganizationService: HttpOrganizationService,
    private toastService:ToastService,
    private activeRoute: ActivatedRoute,
    private router:Router) {
      const pageNo: number = this.activeRoute.snapshot.data['pageNo'];
      this.pageNo = pageNo;
      this.loadFilteredPractitioners();
    }

  ngOnInit(): void {
    this.loadFilterParams();
  }

  loadFilterParams() {
    this.httpOrganizationService
      .findAll()
      .subscribe((orgList) =>  this.setFilterParams(orgList));
  }

  setFilterParams(organizationList:Organization[]) {
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
        key: 'gender', name: 'Gender',inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: Object.entries(Gender)
         .map((item,indexOf) => ({ key: indexOf, name: item[1], isChecked: false}))
      },
      {
        key: 'birthDate', name: 'Birth date', inputType: ParamInputType.DATE, operation: SearchOperation.GREATER_THAN_DATE, isChecked: false,
          value: new DatePipe('en-US').transform(new Date(), 'yyyy-MM-dd')
      },
      {
        key: 'qualification', name:'Qualification', isChecked:false, operation: SearchOperation.EQUAL, inputType: ParamInputType.CHECKBOX,
        value:Object.entries(Qualification)
          .map((item,indexOf) => ({ key: indexOf, name: item[1], isChecked: false}))
      },
      {
        key: 'organization', name: 'Organization', isChecked: false, operation: SearchOperation.EQUAL, inputType: ParamInputType.CHECKBOX,
        value: organizationList
          .map((item) => ({ key: item.idOrganization, name: item.name, isChecked: false}))
      },
      {
        key: 'organization', name: 'Unemployed', isChecked: false, operation: SearchOperation.IS_NULL, inputType: ParamInputType.NULLABLE, value: null
      }
    ];
  }

  loadPractitioners() {
    this.httpPractitionerService
      .getByPage(this.pageNo, this.pageSize, this.direction, this.sortBy)
      .subscribe((practitionerPage) => {
        this.practitionerList = practitionerPage.content;
        this.collectionSize = practitionerPage.totalElements;
        this.pageSize = practitionerPage.size;
      });
  }

  formSearchCriteriaList(searchCriteriaList:SearchCriteria[]){
    this.searchCriteriaList = searchCriteriaList;
    this.loadFilteredPractitioners();
    this.toggleShowfilter();
  }

  loadFilteredPractitioners() {
    this.httpPractitionerService
      .getByFilteredPage(this.pageNo, this.pageSize, this.direction, this.sortBy, this.searchCriteriaList)
      .subscribe((practitionerPage) => {
        this.practitionerList = practitionerPage.content;
        this.collectionSize = practitionerPage.totalElements;
        this.pageSize = practitionerPage.size;
      });
  }

  onPageChange(page: number) {
    if (this.pageNo) this.loadFilteredPractitioners();
  }

  onSort(sortEvent: SortEvent) {
    this.sortBy = sortEvent.column;
    this.direction = sortEvent.direction;
    this.headers?.forEach((header) => {
      if (header.sortable !== sortEvent.column) {
        header.sortDirection = '';
      }
    });
    this.loadFilteredPractitioners();
  }

  onClickEdit(id: number){
    this.router.navigate(['practitioner/practitioner-edit/',id]);
  }
  onClickDetails(id: number){
    this.router.navigate(['practitioner/practitioner-details/',id]);
  }
  onClickDelete(id: number){
    this.httpPractitionerService.delete(id)
    .subscribe((message)=>{
      this.loadFilteredPractitioners();
      this.toastService.showToast("You have successfuly deleted a practitioner.",
      { className: 'bg-success text-light' });
    }
    ,error=>{
      this.toastService.showToast("Practitioner can not be deleted some examination is still running!",
      { className: 'bg-danger text-light' });
    });
  }

  get getGender(){
    return Gender;
  }

  get getQualification(){
    return Qualification;
  }

  toggleShowfilter(){
    this.filterShow = !this.filterShow;
  }
}
