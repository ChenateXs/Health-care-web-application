import { TestBed } from '@angular/core/testing';

import { ListPageNoResolver } from './list-page-no.resolver';

describe('ListPageNoResolver', () => {
  let resolver: ListPageNoResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(ListPageNoResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
