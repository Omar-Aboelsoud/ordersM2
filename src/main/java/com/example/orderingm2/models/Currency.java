package com.example.orderingm2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {
    private String name;
    private BigDecimal currentUsdPrice;;
}
