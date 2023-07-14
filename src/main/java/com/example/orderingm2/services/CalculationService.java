package com.example.orderingm2.services;

import java.math.BigDecimal;

public interface CalculationService {
    BigDecimal addFee(BigDecimal amount, BigDecimal feePercentage);
    BigDecimal removeFee(BigDecimal amountWithFee, BigDecimal feePercentage);
}
