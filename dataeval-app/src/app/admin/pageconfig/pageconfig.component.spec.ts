import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageconfigComponent } from './pageconfig.component';

describe('PageconfigComponent', () => {
  let component: PageconfigComponent;
  let fixture: ComponentFixture<PageconfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageconfigComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageconfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
