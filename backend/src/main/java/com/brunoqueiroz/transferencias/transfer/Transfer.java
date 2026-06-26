package com.brunoqueiroz.transferencias.transfer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transfers")
@Getter
@Setter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceAccount;

    private String destinationAccount;

    private BigDecimal amount;

    private BigDecimal fee;

    private LocalDate transferDate;

    private LocalDate scheduledAt;

}