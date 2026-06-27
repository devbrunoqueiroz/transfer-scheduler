import { Component, Input } from '@angular/core';
import { TransferResponse } from '../../../../core/models/transfer-response';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-transfers-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    CurrencyPipe,
    DatePipe
  ],
  templateUrl: './transfers-list.html',
  styleUrl: './transfers-list.scss',
})
export class TransfersList {
  @Input()
  transfers: TransferResponse[] = [];

  displayedColumns = [
    'origin',
    'destination',
    'amount',
    'fee',
    'transferDate',
    'scheduledAt'
  ];

}
