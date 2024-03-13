import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListPageNoResolver } from 'src/app/shared/resolvers/list-page-no.resolver';
import { PractitionerAddComponent } from './pages/practitioner-add/practitioner-add.component';
import { PractitionerBulkImportComponent } from './pages/practitioner-bulk-import/practitioner-bulk-import.component';
import { PractitionerDetailsComponent } from './pages/practitioner-details/practitioner-details.component';
import { PractitionerEditComponent } from './pages/practitioner-edit/practitioner-edit.component';
import { PractitionerListComponent } from './pages/practitioner-list/practitioner-list.component';

const routes: Routes = [
  {path: 'practitioner-list', component:PractitionerListComponent, resolve: { pageNo: ListPageNoResolver }},
  {path: 'practitioner-add', component:PractitionerAddComponent},
  {path: 'practitioner-bulk-import', component:PractitionerBulkImportComponent},
  {path: 'practitioner-edit/:id', component:PractitionerEditComponent},
  {path: 'practitioner-details/:id', component:PractitionerDetailsComponent},
  {path:'', redirectTo: 'practitioner-list', pathMatch:'full'}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PractitionerRoutingModule { }
