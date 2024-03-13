import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientBulkImportComponent } from './patient-bulk-import.component';

describe('PatientBulkImportComponent', () => {
  let component: PatientBulkImportComponent;
  let fixture: ComponentFixture<PatientBulkImportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientBulkImportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientBulkImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
