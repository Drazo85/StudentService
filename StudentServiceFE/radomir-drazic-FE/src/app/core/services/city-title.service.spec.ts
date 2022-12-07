import { TestBed } from '@angular/core/testing';

import { CityTitleService } from './city-title.service';

describe('CityTitleService', () => {
  let service: CityTitleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CityTitleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
