import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'; 
import { CentresComponent } from './centres.component';

describe('CentresComponent', () => {
  let component: CentresComponent;
  let fixture: ComponentFixture<CentresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CentresComponent, HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
