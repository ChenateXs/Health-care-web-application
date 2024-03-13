import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PractitionerAddComponent } from './practitioner-add.component';

describe('PractitionerAddComponent', () => {
  let component: PractitionerAddComponent;
  let fixture: ComponentFixture<PractitionerAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PractitionerAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PractitionerAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
