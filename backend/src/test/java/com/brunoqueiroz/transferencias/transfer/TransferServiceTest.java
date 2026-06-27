package com.brunoqueiroz.transferencias.transfer;

import static org.junit.jupiter.api.Assertions.*;

import com.brunoqueiroz.transferencias.transfer.dto.CreateTransferRequest;
import com.brunoqueiroz.transferencias.transfer.dto.TransferResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private TransferMapper transferMapper;

    @InjectMocks
    private TransferService transferService;

    @Test
    void shouldCreateTransferSuccessfully() {
        LocalDate localDate = LocalDate.now();

        // Arrange
        CreateTransferRequest request = new CreateTransferRequest();
        request.setOriginAccount("1234567890");
        request.setDestinationAccount("0987654321");
        request.setAmount(new BigDecimal("1000.00"));
        request.setTransferDate(localDate.plusDays(15));

        Transfer transfer = new Transfer();
        transfer.setOriginAccount(request.getOriginAccount());
        transfer.setDestinationAccount(request.getDestinationAccount());
        transfer.setAmount(request.getAmount());
        transfer.setTransferDate(request.getTransferDate());

        TransferResponse response = new TransferResponse();
        response.setId(1L);
        response.setOriginAccount(request.getOriginAccount());
        response.setDestinationAccount(request.getDestinationAccount());
        response.setAmount(request.getAmount());
        response.setTransferDate(request.getTransferDate());

        when(transferMapper.toEntity(request)).thenReturn(transfer);
        when(transferRepository.save(any())).thenReturn(transfer);
        when(transferMapper.toResponse(any())).thenReturn(response);

        // Act
        TransferResponse result = transferService.createTransfer(request);

        // Assert

        ArgumentCaptor<Transfer> captor = ArgumentCaptor.forClass(Transfer.class);

        verify(transferRepository).save(captor.capture());

        Transfer savedTransfer = captor.getValue();

        assertEquals(new BigDecimal("82.00"), savedTransfer.getFee());
        assertEquals(localDate, savedTransfer.getScheduledAt());

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("1234567890", result.getOriginAccount());
        assertEquals("0987654321", result.getDestinationAccount());

        verify(transferMapper).toEntity(request);
        verify(transferMapper).toResponse(transfer);
    }
}