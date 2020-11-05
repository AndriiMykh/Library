import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualOrderComponent } from './actual-order.component';

describe('ActualOrderComponent', () => {
  let component: ActualOrderComponent;
  let fixture: ComponentFixture<ActualOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActualOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActualOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
