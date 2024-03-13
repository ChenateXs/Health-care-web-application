import { TestBed } from '@angular/core/testing';

import { HttpOrganizationTypeService } from './http-organization-type.service';

describe('HttpOrganizationTypeService', () => {
  let service: HttpOrganizationTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpOrganizationTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
