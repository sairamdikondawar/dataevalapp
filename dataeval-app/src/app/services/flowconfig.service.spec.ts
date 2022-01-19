import { TestBed } from '@angular/core/testing';

import { FlowconfigService } from './flowconfig.service';

describe('FlowconfigService', () => {
  let service: FlowconfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlowconfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
