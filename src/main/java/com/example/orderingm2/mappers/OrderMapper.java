package com.example.orderingm2.mappers;

import com.example.orderingm2.config.MapStructConfig;
import com.example.orderingm2.dtos.CreateNewOrderRequest;
import com.example.orderingm2.dtos.OrderDto;
import com.example.orderingm2.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(config = MapStructConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order map(CreateNewOrderRequest createNewOrderRequest);
    OrderDto map(Order order);
    List<OrderDto> map(List<Order> order);
}
