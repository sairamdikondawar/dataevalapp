import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlowconfigComponent } from './flowconfig.component';

describe('FlowconfigComponent', () => {
  let component: FlowconfigComponent;
  let fixture: ComponentFixture<FlowconfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlowconfigComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlowconfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
