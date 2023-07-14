package com.example.orderingm2.services;

import com.example.orderingm2.common.ListResponseWrapper;
import com.example.orderingm2.dtos.CreateNewOrderRequest;
import com.example.orderingm2.dtos.GenericCreationResponse;
import com.example.orderingm2.dtos.OrderDto;
import com.example.orderingm2.models.OrderStatus;

import java.time.LocalDateTime;

public interface OrderService {
    GenericCreationResponse createNewOrder(CreateNewOrderRequest createNewOrderRequest);
    OrderDto findOrder(Long id);
    ListResponseWrapper<OrderDto> findOrders(OrderStatus orderStatus, LocalDateTime creationDateFrom , LocalDateTime creationDateTo, Integer size, Integer page);
}
