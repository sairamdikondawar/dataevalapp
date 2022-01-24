import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserformdetailsComponent } from './userformdetails.component';

describe('UserformdetailsComponent', () => {
  let component: UserformdetailsComponent;
  let fixture: ComponentFixture<UserformdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserformdetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserformdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
