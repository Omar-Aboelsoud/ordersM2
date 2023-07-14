package com.example.orderingm2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class GenericCreationResponse {
    private Long id;
    private LocalDateTime creationDate;
}
