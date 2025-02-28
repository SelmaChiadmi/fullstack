import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MycenterComponent } from './mycenter.component';

describe('MycenterComponent', () => {
  let component: MycenterComponent;
  let fixture: ComponentFixture<MycenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MycenterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MycenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
