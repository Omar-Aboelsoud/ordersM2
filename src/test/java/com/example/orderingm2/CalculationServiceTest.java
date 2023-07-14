package com.example.orderingm2;

import com.example.orderingm2.services.CalculationService;
import com.example.orderingm2.services.CalculationServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculationServiceTest {
    private final CalculationService calculationService = new CalculationServiceImpl();

    @Test
    public void testAddFee() {
        BigDecimal amount = BigDecimal.valueOf(100);
        BigDecimal feePercentage = BigDecimal.valueOf(0.1);
        BigDecimal expectedAmountWithFee = BigDecimal.valueOf(110);

        BigDecimal result = calculationService.addFee(amount, feePercentage);

        assertEquals(expectedAmountWithFee.intValue(), result.intValue());
    }

    @Test
    public void testRemoveFee() {
        BigDecimal amountWithFee = BigDecimal.valueOf(110);
        BigDecimal feePercentage = BigDecimal.valueOf(0.1);
        BigDecimal expectedAmount = BigDecimal.valueOf(99);

        BigDecimal result = calculationService.removeFee(amountWithFee, feePercentage);

        assertEquals(expectedAmount.intValue(), result.intValue());
    }
}
