package com.example.orderingm2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GenericCreationResponse {
    private Long id;
    private LocalDateTime creationDate;
}
