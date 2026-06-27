import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransfersList } from './transfers-list';

describe('TransfersList', () => {
  let component: TransfersList;
  let fixture: ComponentFixture<TransfersList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransfersList],
    }).compileComponents();

    fixture = TestBed.createComponent(TransfersList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
