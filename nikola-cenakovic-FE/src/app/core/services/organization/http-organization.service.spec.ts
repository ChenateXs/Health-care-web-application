import { TestBed } from '@angular/core/testing';

import { HttpOrganizationService } from './http-organization.service';

describe('HttpOrganizationService', () => {
  let service: HttpOrganizationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpOrganizationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
