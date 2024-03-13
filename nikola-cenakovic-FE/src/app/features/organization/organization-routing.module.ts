import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListPageNoResolver } from 'src/app/shared/resolvers/list-page-no.resolver';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationBulkImportComponent } from './pages/organization-bulk-import/organization-bulk-import.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationEditComponent } from './pages/organization-edit/organization-edit.component';
import { OrganizationListComponent } from './pages/organization-list/organization-list.component';

const routes: Routes = [
  {path: 'organization-list', component:OrganizationListComponent, resolve: { pageNo: ListPageNoResolver }},
  {path: 'organization-add', component:OrganizationAddComponent},
  {path: 'organization-bulk-import', component:OrganizationBulkImportComponent},
  {path: 'organization-details/:id', component:OrganizationDetailsComponent},
  {path: 'organization-edit/:id', component:OrganizationEditComponent},
  {path:'', redirectTo: 'organization-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrganizationRoutingModule { }
