import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmRdvComponent} from './confirm-rdv.component';

describe('ConfirmRdvComponent', () => {
  let component: ConfirmRdvComponent;
  let fixture: ComponentFixture<ConfirmRdvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirmRdvComponent,HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmRdvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
