package com.brunoqueiroz.transferencias.transfer;

import com.brunoqueiroz.transferencias.exceptions.TransferNotFoundException;
import com.brunoqueiroz.transferencias.transfer.dto.CreateTransferRequest;
import com.brunoqueiroz.transferencias.transfer.dto.TransferResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    public TransferService(TransferRepository transferRepository, TransferMapper transferMapper) {
        this.transferRepository = transferRepository;
        this.transferMapper = transferMapper;
    }

    public Transfer getTransfer(Long id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));
    }

    public List<TransferResponse> getAllTransfers() {
        List<Transfer> transfers = transferRepository.findAll();
        return transfers.stream()
                .map(transferMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TransferResponse createTransfer(CreateTransferRequest request) {
        Transfer transfer = transferMapper.toEntity(request);



        transferRepository.save(transfer);
        return transferMapper.toResponse(transfer);
    }
}
