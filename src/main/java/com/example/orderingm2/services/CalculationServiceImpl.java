package com.example.orderingm2.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationServiceImpl implements CalculationService {
    // Function: Add a fee to an amount
    @Override
    public BigDecimal addFee(BigDecimal amount, BigDecimal feePercentage) {
        BigDecimal fee = amount.multiply(feePercentage);
        return amount.add(fee);
    }

    // Function: Remove a fee to find the amount before fee
    @Override
    public BigDecimal removeFee(BigDecimal amountWithFee, BigDecimal feePercentage) {
        BigDecimal fee = amountWithFee.multiply(feePercentage);
        return amountWithFee.subtract(fee);
    }
}
