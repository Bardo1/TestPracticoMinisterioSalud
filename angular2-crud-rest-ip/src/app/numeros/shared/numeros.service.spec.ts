/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { numerosService } from './numeros.service';

describe('Service: numeros', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [numerosService]
    });
  });

  it('should ...', inject([numerosService], (service: numerosService) => {
    expect(service).toBeTruthy();
  }));
});
