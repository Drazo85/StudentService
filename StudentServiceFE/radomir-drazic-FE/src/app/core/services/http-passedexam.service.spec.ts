import { TestBed } from '@angular/core/testing';

import { HttpPassedexamService } from './http-passedexam.service';

describe('HttpPassedexamService', () => {
  let service: HttpPassedexamService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpPassedexamService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
