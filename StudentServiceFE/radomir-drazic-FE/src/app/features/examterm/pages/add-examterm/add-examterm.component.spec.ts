import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExamtermComponent } from './add-examterm.component';

describe('AddExamtermComponent', () => {
  let component: AddExamtermComponent;
  let fixture: ComponentFixture<AddExamtermComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddExamtermComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddExamtermComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
