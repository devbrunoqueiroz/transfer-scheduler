package com.brunoqueiroz.transferencias.transfer;

import com.brunoqueiroz.transferencias.transfer.dto.CreateTransferRequest;
import com.brunoqueiroz.transferencias.transfer.dto.TransferResponse;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    public Transfer toEntity(CreateTransferRequest request) {

        Transfer transfer = new Transfer();

        transfer.setOriginAccount(request.getOriginAccount());
        transfer.setDestinationAccount(request.getDestinationAccount());
        transfer.setAmount(request.getAmount());
        transfer.setTransferDate(request.getTransferDate());

        return transfer;
    }

    public TransferResponse toResponse(Transfer transfer) {

        TransferResponse response = new TransferResponse();

        response.setId(transfer.getId());
        response.setOriginAccount(transfer.getOriginAccount());
        response.setDestinationAccount(transfer.getDestinationAccount());
        response.setAmount(transfer.getAmount());
        response.setFee(transfer.getFee());
        response.setTransferDate(transfer.getTransferDate());
        response.setScheduledAt(transfer.getScheduledAt());

        return response;
    }
}
