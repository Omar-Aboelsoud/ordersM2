package com.example.orderingm2.dtos;

import com.example.orderingm2.models.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateNewOrderRequest {
    @NotNull(message = "Quantity can not be empty or null")
    private BigDecimal quantity;
    @NotNull(message = "Quantity can not be empty or null")
    private Currency currency;
}
