export interface TransferResponse {

  id: number;
  originAccount: string;
  destinationAccount: string;
  amount: number;
  fee: number;
  transferDate: string;
  scheduledAt: string;

}