import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransferResponse } from '../models/transfer-response';
import { CreateTransferRequest } from '../models/create-transfer-request';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private readonly api =
      'http://localhost:8080/transfers';

  constructor(
      private http: HttpClient) {
  }

  getAll() {
      return this.http.get<TransferResponse[]>(this.api);
  }

  create(request: CreateTransferRequest) {
      return this.http.post<TransferResponse>(this.api, request);
  }

}