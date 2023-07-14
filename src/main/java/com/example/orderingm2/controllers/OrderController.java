package com.example.orderingm2.controllers;

import com.example.orderingm2.common.Constants;
import com.example.orderingm2.common.ListResponseWrapper;
import com.example.orderingm2.dtos.CreateNewOrderRequest;
import com.example.orderingm2.dtos.GenericCreationResponse;
import com.example.orderingm2.dtos.OrderDto;
import com.example.orderingm2.models.OrderStatus;
import com.example.orderingm2.services.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.example.orderingm2.controllers.OrderController.ORDERS_BASE_URL;

@RestController
@RequestMapping(ORDERS_BASE_URL)
public class OrderController {

    static final String ORDERS_BASE_URL = Constants.API_BASE_URL + "/orders";
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<GenericCreationResponse> createNewOrder(@RequestBody CreateNewOrderRequest createNewOrderRequest) {
        return new ResponseEntity<>(orderService.createNewOrder(createNewOrderRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.findOrder(orderId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListResponseWrapper<OrderDto>> findOrdersByStatusAndCreationDate(
            @RequestParam(defaultValue = "OPEN", required = false) OrderStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "1") Integer page) {
        return new ResponseEntity<>(orderService.findOrders(status, startDate, endDate, size, page), HttpStatus.NOT_FOUND);
    }
}
