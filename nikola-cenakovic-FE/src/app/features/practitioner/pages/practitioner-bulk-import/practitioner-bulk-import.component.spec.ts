import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PractitionerBulkImportComponent } from './practitioner-bulk-import.component';

describe('PractitionerBulkImportComponent', () => {
  let component: PractitionerBulkImportComponent;
  let fixture: ComponentFixture<PractitionerBulkImportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PractitionerBulkImportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PractitionerBulkImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
