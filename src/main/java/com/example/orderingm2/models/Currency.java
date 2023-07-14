package com.example.orderingm2.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {
    private String name;
    private BigDecimal currentUsdPrice;
}
