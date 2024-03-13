import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PractitionerRoutingModule } from './practitioner-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { PractitionerListComponent } from './pages/practitioner-list/practitioner-list.component';
import { PractitionerDetailsComponent } from './pages/practitioner-details/practitioner-details.component';
import { PractitionerAddComponent } from './pages/practitioner-add/practitioner-add.component';
import { PractitionerEditComponent } from './pages/practitioner-edit/practitioner-edit.component';
import { PractitionerBulkImportComponent } from './pages/practitioner-bulk-import/practitioner-bulk-import.component';


@NgModule({
  declarations: [
    PractitionerListComponent,
    PractitionerDetailsComponent,
    PractitionerAddComponent,
    PractitionerEditComponent,
    PractitionerBulkImportComponent
  ],
  imports: [
    CommonModule,
    PractitionerRoutingModule,
    SharedModule
  ]
})
export class PractitionerModule { }
