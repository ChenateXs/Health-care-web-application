import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FilterParam, Organization, OrganizationType, PageDto, ParamInputType } from 'src/app/core/models';
import { SearchCriteria } from 'src/app/core/models/dto/search-criteria.dto';
import { SearchOperation } from 'src/app/core/models/enum/search-operation.enum';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';
import { HttpOrganizationTypeService } from 'src/app/core/services/organizationType/http-organization-type.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import {
  SortableHeaderDirective,
  SortEvent,
} from 'src/app/shared/directives/sortable-header/sortable-header.directive';

@Component({
  selector: 'app-organization-list',
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css'],
})
export class OrganizationListComponent implements OnInit {

  filterParams!: FilterParam[];
  organizationList!: Organization[];
  searchCriteriaList: SearchCriteria[] = [];

  filterShow: boolean = false;

  pageNo: number = 0;
  pageSize: number = 10;
  direction: string = 'ASC';
  sortBy: string = 'identifier';
  collectionSize: number = 100;

  @ViewChildren(SortableHeaderDirective)
  headers?: QueryList<SortableHeaderDirective>;

  constructor(
    private httpOrganizationTypeService: HttpOrganizationTypeService,
    private httpOrganizationService: HttpOrganizationService,
    private toastService:ToastService,
    private activeRoute: ActivatedRoute,
    private router: Router
  ) {
    const pageNo: number = this.activeRoute.snapshot.data['pageNo'];
    this.pageNo = pageNo;
    this.loadFilteredOrganizations();
  }

  ngOnInit(): void {
    this.loadFilterParams();
  }

  loadFilterParams() {
    this.httpOrganizationTypeService
      .findAll()
      .subscribe((orgTypeList) =>  this.setFilterParams(orgTypeList));
  }

  setFilterParams(organizationTypeList:OrganizationType[]) {
    this.filterParams = [
      {
        key: 'identifier', name: 'Identifier', inputType: ParamInputType.TEXT, operation: SearchOperation.LIKE, isChecked: false, value: ''
      },
      {
        key: 'organizationType', name: 'Organization type',inputType: ParamInputType.CHECKBOX, operation: SearchOperation.EQUAL, isChecked: false,
        value: organizationTypeList
        .map((item) => ({ key: item.idOrganizationType, name: item.name, isChecked: false}))
      },
      {
        key: 'name',  name: 'Name', inputType: ParamInputType.TEXT, operation: SearchOperation.LIKE, isChecked: false, value: ''
      }
    ];
  }

  loadOrganization() {
    this.httpOrganizationService
      .getByPage(this.pageNo, this.pageSize, this.direction, this.sortBy)
      .subscribe((organizationPage) => {
        this.organizationList = organizationPage.content;
        this.collectionSize = organizationPage.totalElements;
        this.pageSize = organizationPage.size;
      });
  }

  formSearchCriteriaList(searchCriteriaList:SearchCriteria[]){
    this.searchCriteriaList = searchCriteriaList;
    this.loadFilteredOrganizations();
    this.toggleShowfilter();
  }

  loadFilteredOrganizations() {
    this.httpOrganizationService
      .getByFilteredPage(this.pageNo, this.pageSize, this.direction, this.sortBy, this.searchCriteriaList)
      .subscribe((organizationPage) => {
        console.log(organizationPage);
        this.organizationList = organizationPage.content;
        this.collectionSize = organizationPage.totalElements;
        this.pageSize = organizationPage.size;
      });
  }

  onPageChange(number: number) {
    if (this.pageNo)
      this.loadFilteredOrganizations();
  }

  onSort(sortEvent: SortEvent) {
    this.sortBy = sortEvent.column;
    this.direction = sortEvent.direction;
    this.headers?.forEach((header) => {
      if (header.sortable !== sortEvent.column) {
        header.sortDirection = '';
      }
    });
    this.loadFilteredOrganizations();
  }

  getName(element: any) {
    return element.name;
  }

  onClickEdit(id: number) {
    this.router.navigate(['organization/organization-edit/', id], {
      queryParams: { pageNo: this.pageNo },
    });
  }

  onClickDetails(id: number) {
    this.router.navigate(['organization/organization-details/', id], {
      queryParams: { pageNo: this.pageNo },
    });
  }

  onClickDelete(id: number) {
    this.httpOrganizationService
      .delete(id)
      .subscribe(()=>{
        this.loadFilteredOrganizations();
        this.toastService.showToast("Organization have successfully been deleted.",
              { className: 'bg-success text-light' });
      },err=>{
        this.toastService.showToast("Organization can not be deleted some examinations are still running!",
              { className: 'bg-danger text-light' });
      })
  }

  toggleShowfilter(){
    this.filterShow = !this.filterShow;
  }
}
