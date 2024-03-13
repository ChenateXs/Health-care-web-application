import { TestBed } from '@angular/core/testing';

import { HttpExaminationService } from './http-examination.service';

describe('HttpExaminationService', () => {
  let service: HttpExaminationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpExaminationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
