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

    private String accOrigin;

    private String accDestiny;

    private BigDecimal value;

    private BigDecimal tax;

    private LocalDate transferDate;

    private LocalDate dueDate;

}