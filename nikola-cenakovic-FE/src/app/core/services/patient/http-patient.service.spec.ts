import { TestBed } from '@angular/core/testing';

import { HttpPatientService } from './http-patient.service';

describe('HttpPatientService', () => {
  let service: HttpPatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpPatientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
