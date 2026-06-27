package com.brunoqueiroz.transferencias.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {

    private Long id;

    private String originAccount;

    private String destinationAccount;

    private BigDecimal amount;

    private BigDecimal fee;

    private LocalDate transferDate;

    private LocalDate scheduledAt;
}