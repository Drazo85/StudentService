import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivestudentsListComponent } from './activestudents-list.component';

describe('ActivestudentsListComponent', () => {
  let component: ActivestudentsListComponent;
  let fixture: ComponentFixture<ActivestudentsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActivestudentsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivestudentsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
