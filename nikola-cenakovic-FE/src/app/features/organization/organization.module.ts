import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrganizationRoutingModule } from './organization-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { OrganizationListComponent } from './pages/organization-list/organization-list.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationEditComponent } from './pages/organization-edit/organization-edit.component';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationBulkImportComponent } from './pages/organization-bulk-import/organization-bulk-import.component';


@NgModule({
  declarations: [
    OrganizationListComponent,
    OrganizationDetailsComponent,
    OrganizationEditComponent,
    OrganizationAddComponent,
    OrganizationBulkImportComponent
  ],
  imports: [
    CommonModule,
    OrganizationRoutingModule,
    SharedModule
  ]
})
export class OrganizationModule { }
