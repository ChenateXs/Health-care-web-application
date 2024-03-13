import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationBulkImportComponent } from './examination-bulk-import.component';

describe('ExaminationBulkImportComponent', () => {
  let component: ExaminationBulkImportComponent;
  let fixture: ComponentFixture<ExaminationBulkImportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationBulkImportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationBulkImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
