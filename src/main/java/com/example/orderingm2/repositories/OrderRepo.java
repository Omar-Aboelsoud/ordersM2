package com.example.orderingm2.repositories;

import com.example.orderingm2.models.Order;
import com.example.orderingm2.models.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("select o from Order o where" +
            " (o.status = :status)" +
            "and " +
            "o.creationDate >= :creationTimeFrom and o.creationDate <=:creationTimeTo ")
    Page<Order> findOrdersByStatusAndDate(OrderStatus status, LocalDateTime creationTimeFrom , LocalDateTime creationTimeTo , Pageable pageable);
 }
