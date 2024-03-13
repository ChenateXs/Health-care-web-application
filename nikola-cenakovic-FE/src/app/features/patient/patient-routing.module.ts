import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListPageNoResolver } from 'src/app/shared/resolvers/list-page-no.resolver';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';
import { PatientBulkImportComponent } from './pages/patient-bulk-import/patient-bulk-import.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientEditComponent } from './pages/patient-edit/patient-edit.component';
import { PatientListComponent } from './pages/patient-list/patient-list.component';

const routes: Routes = [
  {path: 'patient-list', component:PatientListComponent, resolve: { pageNo: ListPageNoResolver }},
  {path: 'patient-add', component:PatientAddComponent},
  {path: 'patient-bulk-import', component:PatientBulkImportComponent},
  {path: 'patient-edit/:id', component:PatientEditComponent},
  {path: 'patient-details/:id', component:PatientDetailsComponent},
  {path:'', redirectTo: 'patient-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
