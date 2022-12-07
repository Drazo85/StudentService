import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessoractiveexamsListComponent } from './professoractiveexams-list.component';

describe('ProfessoractiveexamsListComponent', () => {
  let component: ProfessoractiveexamsListComponent;
  let fixture: ComponentFixture<ProfessoractiveexamsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessoractiveexamsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessoractiveexamsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
