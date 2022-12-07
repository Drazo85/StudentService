import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamtermListComponent } from './examterm-list.component';

describe('ExamtermListComponent', () => {
  let component: ExamtermListComponent;
  let fixture: ComponentFixture<ExamtermListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamtermListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamtermListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
