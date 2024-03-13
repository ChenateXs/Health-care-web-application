import { TestBed } from '@angular/core/testing';

import { HttpServiceTypeService } from './http-service-type.service';

describe('HttpServiceTypeService', () => {
  let service: HttpServiceTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpServiceTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
