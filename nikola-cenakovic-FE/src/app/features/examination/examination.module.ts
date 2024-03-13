import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExaminationRoutingModule } from './examination-routing.module';
import { ExaminationListComponent } from './pages/examination-list/examination-list.component';
import { ExaminationEditComponent } from './pages/examination-edit/examination-edit.component';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ExaminationBulkImportComponent } from './pages/examination-bulk-import/examination-bulk-import.component';


@NgModule({
  declarations: [
    ExaminationListComponent,
    ExaminationEditComponent,
    ExaminationAddComponent,
    ExaminationDetailsComponent,
    ExaminationBulkImportComponent
  ],
  imports: [
    CommonModule,
    ExaminationRoutingModule,
    SharedModule
  ]
})
export class ExaminationModule { }
