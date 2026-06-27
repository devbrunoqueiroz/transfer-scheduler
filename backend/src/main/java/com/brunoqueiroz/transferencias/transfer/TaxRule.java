package com.brunoqueiroz.transferencias.transfer;

import com.brunoqueiroz.transferencias.exceptions.NoApplicableTaxException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public enum TaxRule {

    SAME_DAY(0,0, new BigDecimal("3.00"), new BigDecimal("0.025")),

    ONE_TO_TEN( 1,10, new BigDecimal("12.00"),BigDecimal.ZERO),

    ELEVEN_TO_TWENTY(11,20, BigDecimal.ZERO, new BigDecimal("0.082")),

    TWENTY_ONE_TO_THIRTY(21,30, BigDecimal.ZERO, new BigDecimal("0.069")),

    THIRTY_ONE_TO_FORTY(31,40, BigDecimal.ZERO, new BigDecimal("0.047")),

    FORTY_ONE_TO_FIFTY(41,50, BigDecimal.ZERO, new BigDecimal("0.017"));

    private final int minDays;
    private final int maxDays;
    private final BigDecimal fixedFee;
    private final BigDecimal percentage;

    TaxRule(int minDays, int maxDays, BigDecimal fixedFee, BigDecimal percentage) {
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.fixedFee = fixedFee;
        this.percentage = percentage;
    }

    public boolean matches(long days) {
        return days >= minDays && days <= maxDays;
    }

    public BigDecimal calculate(BigDecimal amount) {

        return fixedFee.add(
                amount.multiply(percentage)
                        .setScale(2, RoundingMode.HALF_UP)
        );
    }

    public static TaxRule of(long days) {

        return Arrays.stream(values())
                .filter(rule -> rule.matches(days))
                .findFirst()
                .orElseThrow(() ->
                        new NoApplicableTaxException(days));
    }
}