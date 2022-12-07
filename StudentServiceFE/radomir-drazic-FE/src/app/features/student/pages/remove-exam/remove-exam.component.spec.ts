import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveExamComponent } from './remove-exam.component';

describe('RemoveExamComponent', () => {
  let component: RemoveExamComponent;
  let fixture: ComponentFixture<RemoveExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RemoveExamComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemoveExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
