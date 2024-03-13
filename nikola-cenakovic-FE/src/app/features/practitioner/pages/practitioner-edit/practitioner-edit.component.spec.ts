import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PractitionerEditComponent } from './practitioner-edit.component';

describe('PractitionerEditComponent', () => {
  let component: PractitionerEditComponent;
  let fixture: ComponentFixture<PractitionerEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PractitionerEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PractitionerEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
