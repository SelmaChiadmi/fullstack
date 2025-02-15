import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriseRdvVerifMailComponent } from './prise-rdv-verif-mail.component';

describe('PriseRdvMailConnuComponent', () => {
  let component: PriseRdvVerifMailComponent;
  let fixture: ComponentFixture<PriseRdvVerifMailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PriseRdvVerifMailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PriseRdvVerifMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
