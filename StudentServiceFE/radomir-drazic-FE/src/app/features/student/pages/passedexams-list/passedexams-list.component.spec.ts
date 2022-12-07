import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassedexamsListComponent } from './passedexams-list.component';

describe('PassedexamsListComponent', () => {
  let component: PassedexamsListComponent;
  let fixture: ComponentFixture<PassedexamsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassedexamsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassedexamsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
