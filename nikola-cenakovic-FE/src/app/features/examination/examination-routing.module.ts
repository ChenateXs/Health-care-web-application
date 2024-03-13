import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListPageNoResolver } from 'src/app/shared/resolvers/list-page-no.resolver';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationBulkImportComponent } from './pages/examination-bulk-import/examination-bulk-import.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { ExaminationEditComponent } from './pages/examination-edit/examination-edit.component';
import { ExaminationListComponent } from './pages/examination-list/examination-list.component';

const routes: Routes = [
  {path: 'examination-list', component:ExaminationListComponent, resolve: { pageNo: ListPageNoResolver }},
  {path: 'examination-add', component:ExaminationAddComponent},
  {path: 'examination-bulk-import', component:ExaminationBulkImportComponent},
  {path: 'examination-details/:id', component:ExaminationDetailsComponent},
  {path: 'examination-edit/:id', component:ExaminationEditComponent},
  {path:'', redirectTo: 'examination-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExaminationRoutingModule { }
