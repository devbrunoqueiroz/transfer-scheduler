package com.brunoqueiroz.transferencias.transfer;

import com.brunoqueiroz.transferencias.transfer.dto.CreateTransferRequest;
import com.brunoqueiroz.transferencias.transfer.dto.TransferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfers")
    public ResponseEntity<TransferResponse> createTransfer(@Valid @RequestBody CreateTransferRequest request) {
        TransferResponse response = transferService.createTransfer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/transfers")
    public ResponseEntity<List<TransferResponse>> getAllTransfers() {
        List<TransferResponse> transfers = transferService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }

}
