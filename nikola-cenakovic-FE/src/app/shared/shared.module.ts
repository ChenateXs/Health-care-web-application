import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'
import { NgbPaginationModule, NgbToastModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { HeaderComponent } from './components/header/header.component';
import { StringToEnumPipe } from './pipes/string-to-enum/string-to-enum.pipe';
import { SortableHeaderDirective } from './directives/sortable-header/sortable-header.directive';
import { FilterComponent } from './components/filter/filter.component';
import { NamingManagerPipe } from './pipes/naming-manager/naming-manager.pipe';
import { GlobalToastComponent } from './components/global-toast/global-toast.component';
import { FooterComponent } from './components/footer/footer.component';



@NgModule({
  declarations: [
    HeaderComponent,
    StringToEnumPipe,
    SortableHeaderDirective,
    FilterComponent,
    NamingManagerPipe,
    GlobalToastComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    NgbTooltipModule,
    NgbPaginationModule,
    NgbToastModule,
    TranslateModule
  ],
  exports:[
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    NgbTooltipModule,
    NgbPaginationModule,
    NgbToastModule,
    TranslateModule,
    HeaderComponent,
    StringToEnumPipe,
    SortableHeaderDirective,
    FilterComponent,
    NamingManagerPipe,
    GlobalToastComponent,
    FooterComponent
  ]
})
export class SharedModule { }
