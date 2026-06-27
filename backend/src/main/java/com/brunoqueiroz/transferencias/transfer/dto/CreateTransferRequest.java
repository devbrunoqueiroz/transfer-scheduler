package com.brunoqueiroz.transferencias.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferRequest {

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    private String originAccount;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    private String destinationAccount;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private LocalDate transferDate;

}
