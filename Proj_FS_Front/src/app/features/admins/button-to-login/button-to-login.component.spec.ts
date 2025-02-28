import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'; 
import { ButtonToLoginComponent } from './button-to-login.component';

describe('ButtonToLoginComponent', () => {
  let component: ButtonToLoginComponent;
  let fixture: ComponentFixture<ButtonToLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ButtonToLoginComponent,HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ButtonToLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
