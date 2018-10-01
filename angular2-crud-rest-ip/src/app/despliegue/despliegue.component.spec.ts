import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DespliegueComponent } from './despliegue.component';

describe('DespliegueComponent', () => {
  let component: DespliegueComponent;
  let fixture: ComponentFixture<DespliegueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DespliegueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DespliegueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
