import { TestBed } from '@angular/core/testing';

import { HttpExamTermService } from './http-exam-term.service';

describe('HttpExamTermService', () => {
  let service: HttpExamTermService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpExamTermService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
