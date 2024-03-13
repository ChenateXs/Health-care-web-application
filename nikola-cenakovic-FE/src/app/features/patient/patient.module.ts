import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRoutingModule } from './patient-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { PatientListComponent } from './pages/patient-list/patient-list.component';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';
import { PatientEditComponent } from './pages/patient-edit/patient-edit.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientBulkImportComponent } from './pages/patient-bulk-import/patient-bulk-import.component';


@NgModule({
  declarations: [
    PatientListComponent,
    PatientAddComponent,
    PatientEditComponent,
    PatientDetailsComponent,
    PatientBulkImportComponent
  ],
  imports: [
    CommonModule,
    PatientRoutingModule,
    SharedModule
  ]
})
export class PatientModule { }
