import { TestBed } from '@angular/core/testing';

import { HttpPractitionerService } from './http-practitioner.service';

describe('HttpPractitionerService', () => {
  let service: HttpPractitionerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpPractitionerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
