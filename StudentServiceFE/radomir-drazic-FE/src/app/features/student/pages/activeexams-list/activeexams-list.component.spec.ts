import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveexamsListComponent } from './activeexams-list.component';

describe('ActiveexamsListComponent', () => {
  let component: ActiveexamsListComponent;
  let fixture: ComponentFixture<ActiveexamsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveexamsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveexamsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
