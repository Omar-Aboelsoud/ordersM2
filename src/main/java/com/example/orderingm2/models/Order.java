package com.example.orderingm2.models;

import com.example.orderingm2.models.converters.CurrencyConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "m2_order")
@Data
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal quantity;
    @Convert(converter = CurrencyConverter.class)
    private Currency currency;
}
