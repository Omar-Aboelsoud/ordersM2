package com.example.orderingm2.services;

import com.example.orderingm2.Utilties.JpaPageUtil;
import com.example.orderingm2.common.ListResponseWrapper;
import com.example.orderingm2.common.exception.LogicalException;
import com.example.orderingm2.common.exception.ServerError;
import com.example.orderingm2.dtos.CreateNewOrderRequest;
import com.example.orderingm2.dtos.GenericCreationResponse;
import com.example.orderingm2.dtos.OrderDto;
import com.example.orderingm2.mappers.OrderMapper;
import com.example.orderingm2.models.Order;
import com.example.orderingm2.models.OrderStatus;
import com.example.orderingm2.repositories.OrderRepo;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepo orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    @Override
    public GenericCreationResponse createNewOrder(CreateNewOrderRequest createNewOrderRequest) {
        try {
            logger.info().message("Creating new Order").field("createNewOrderRequest", createNewOrderRequest).log();
            Order order = orderMapper.map(createNewOrderRequest);
            order.setStatus(OrderStatus.OPEN);
            Order createdOrder = orderRepo.save(order);
            return new GenericCreationResponse(createdOrder.getId(), createdOrder.getCreationDate());
        } catch (LogicalException e) {
            logger.error().exception("Logical exception happened while creating new order", e)
                    .field("createNewOrderRequest", createNewOrderRequest)
                    .log();
            throw new LogicalException(ServerError.NEW_ORDER_CREATION_FAILED);
        }
    }

    @Override
    public OrderDto findOrder(Long id) {
        try {
            Optional<Order> order = orderRepo.findById(id);
            if (order.isEmpty()) {
                throw new LogicalException(ServerError.ORDER_WITH_SPECIFIED_ID_NOT_EXIST).apply(id);
            }
            return orderMapper.map(order.get());
        } catch (LogicalException e) {
            logger.error().exception("Logical exception happened while getting order by id", e).field("id", id).log();
            throw new LogicalException(ServerError.FAILED_TO_FIND_ORDER).apply(id);
        }
    }

    @Override
    public ListResponseWrapper<OrderDto> findOrders(OrderStatus orderStatus, LocalDateTime creationDateFrom, LocalDateTime creationDateTo, Integer size, Integer page) {
        try {
            Pageable pageable = JpaPageUtil.getPage(page, size);
            Page<Order> orderPage = orderRepo.findOrdersByStatusAndDate(orderStatus, creationDateFrom, creationDateTo, pageable);
            if (orderPage.isEmpty()) {
                logger.info().message("No orders found")
                        .field("orderStatus", orderStatus)
                        .field("creationDateFrom", creationDateFrom)
                        .field("creationDateTo", creationDateTo)
                        .log();
                throw new LogicalException(ServerError.NO_ORDERS_FOUND);
            }
            List<OrderDto> orderDtoList = orderMapper.map(orderPage.getContent());
            return new ListResponseWrapper<>(orderDtoList, orderPage.getTotalPages(), orderPage.getTotalElements());
        } catch (LogicalException e) {
            logger.error().exception("Logical exception happened while getting orders", e)
                    .field("orderStatus", orderStatus)
                    .log();
            throw e;
        } catch (Exception e) {
            logger.error().exception("Logical exception happened while getting orders", e)
                    .field("orderStatus", orderStatus)
                    .log();
            throw new LogicalException(ServerError.FAILED_TO_FIND_ORDERS);
        }
    }
}
