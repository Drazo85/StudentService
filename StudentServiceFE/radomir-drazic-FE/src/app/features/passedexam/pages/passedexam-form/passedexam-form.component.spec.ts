import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassedexamFormComponent } from './passedexam-form.component';

describe('PassedexamFormComponent', () => {
  let component: PassedexamFormComponent;
  let fixture: ComponentFixture<PassedexamFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassedexamFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassedexamFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
