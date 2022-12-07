import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamtermDetailsComponent } from './examterm-details.component';

describe('ExamtermDetailsComponent', () => {
  let component: ExamtermDetailsComponent;
  let fixture: ComponentFixture<ExamtermDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamtermDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamtermDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
