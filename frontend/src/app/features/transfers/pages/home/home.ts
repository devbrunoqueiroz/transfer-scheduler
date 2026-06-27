import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { TransferResponse } from '../../../../core/models/transfer-response';
import { TransferService } from '../../../../core/services/transfer-service';
import { Transfers } from '../../components/transfers-form/transfers';
import { TransfersList } from '../../components/transfers-list/transfers-list';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    Transfers,
    TransfersList
  ],

  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home implements OnInit {

  transfers: TransferResponse[] = [];

  constructor(private transferService: TransferService, private cdr: ChangeDetectorRef ) {}

  ngOnInit(): void {
    this.loadTransfers();
  }

  loadTransfers(): void {
    this.transferService.getAll()
      .subscribe(transfers => {
        this.transfers = transfers;
        this.cdr.detectChanges();
      });
  }
}
