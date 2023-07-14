package com.example.orderingm2.dtos;

import com.example.orderingm2.models.Currency;
import com.example.orderingm2.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderDto {
    private OrderStatus status;
    private BigDecimal quantity;
    private Currency currency;
}
