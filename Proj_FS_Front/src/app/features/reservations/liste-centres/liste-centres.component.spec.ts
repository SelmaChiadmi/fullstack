import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeCentresComponent } from './liste-centres.component';

describe('ListeCentresComponent', () => {
  let component: ListeCentresComponent;
  let fixture: ComponentFixture<ListeCentresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListeCentresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListeCentresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
