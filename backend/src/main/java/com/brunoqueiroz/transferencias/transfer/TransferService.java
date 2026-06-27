package com.brunoqueiroz.transferencias.transfer;

import com.brunoqueiroz.transferencias.transfer.dto.CreateTransferRequest;
import com.brunoqueiroz.transferencias.transfer.dto.TransferResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public List<TransferResponse> getAllTransfers() {
        List<Transfer> transfers = transferRepository.findAll();
        return transfers.stream()
                .map(transferMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TransferResponse createTransfer(CreateTransferRequest request) {
        Transfer transfer = transferMapper.toEntity(request);

        LocalDate scheduledAt = LocalDate.now();

        long days = ChronoUnit.DAYS.between(
                scheduledAt,
                request.getTransferDate());

        TaxRule rule = TaxRule.of(days);

        BigDecimal fee = rule.calculate(request.getAmount());

        transfer.setFee(fee);
        transfer.setScheduledAt(scheduledAt);
        transferRepository.save(transfer);

        return transferMapper.toResponse(transfer);
    }
}
