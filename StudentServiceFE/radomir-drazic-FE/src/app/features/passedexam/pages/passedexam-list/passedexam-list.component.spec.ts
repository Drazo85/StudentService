import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassedexamListComponent } from './passedexam-list.component';

describe('PassedexamListComponent', () => {
  let component: PassedexamListComponent;
  let fixture: ComponentFixture<PassedexamListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassedexamListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassedexamListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
