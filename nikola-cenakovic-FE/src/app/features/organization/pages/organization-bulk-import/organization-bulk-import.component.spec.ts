import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationBulkImportComponent } from './organization-bulk-import.component';

describe('OrganizationBulkImportComponent', () => {
  let component: OrganizationBulkImportComponent;
  let fixture: ComponentFixture<OrganizationBulkImportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrganizationBulkImportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizationBulkImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
