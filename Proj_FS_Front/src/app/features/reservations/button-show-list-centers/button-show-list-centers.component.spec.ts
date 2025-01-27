import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonShowListCentersComponent } from './button-show-list-centers.component';

describe('ButtonShowListCentersComponent', () => {
  let component: ButtonShowListCentersComponent;
  let fixture: ComponentFixture<ButtonShowListCentersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ButtonShowListCentersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ButtonShowListCentersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
