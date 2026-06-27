package com.brunoqueiroz.transferencias.transfer;

import com.brunoqueiroz.transferencias.exceptions.NoApplicableTaxException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxRuleTest {

    @Test
    void shouldCalculateSameDayFee() {

        BigDecimal fee = TaxRule.of(0).calculate(new BigDecimal("1000"));

        assertEquals(new BigDecimal("28.00"), fee);
    }

    @Test
    void shouldCalculateOneToTenDaysFee() {

        BigDecimal fee = TaxRule.of(5).calculate(new BigDecimal("1000"));

        assertEquals(new BigDecimal("12.00"), fee);
    }

    @Test
    void shouldCalculateElevenToTwentyDaysFee() {

        BigDecimal fee = TaxRule.of(15).calculate(new BigDecimal("1000"));

        assertEquals( new BigDecimal("82.00"), fee);
    }
    @Test
    void shouldCalculateTwentyOneToThirtyDaysFee() {

        BigDecimal fee = TaxRule.of(25).calculate(new BigDecimal("1000"));

        assertEquals( new BigDecimal("69.00"), fee);
    }

    @Test
    void shouldCalculateThirtyOneToFortyDaysFee() {

        BigDecimal fee = TaxRule.of(35).calculate(new BigDecimal("1000"));

        assertEquals( new BigDecimal("47.00"), fee);
    }

    @Test
    void shouldCalculateFortyOneToFiftyDaysFee() {

        BigDecimal fee = TaxRule.of(45).calculate(new BigDecimal("1000"));

        assertEquals(new BigDecimal("17.00"), fee);
    }

    @Test
    void shouldThrowExceptionWhenNoTaxRuleExists() {
        assertThrows(NoApplicableTaxException.class, () -> TaxRule.of(60)
        );
    }

}