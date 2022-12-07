import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamtermFormComponent } from './examterm-form.component';

describe('ExamtermFormComponent', () => {
  let component: ExamtermFormComponent;
  let fixture: ComponentFixture<ExamtermFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamtermFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamtermFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
