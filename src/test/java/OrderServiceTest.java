import com.example.orderingm2.common.exception.LogicalException;
import com.example.orderingm2.dtos.CreateNewOrderRequest;
import com.example.orderingm2.dtos.GenericCreationResponse;
import com.example.orderingm2.dtos.OrderDto;
import com.example.orderingm2.mappers.OrderMapper;
import com.example.orderingm2.models.Currency;
import com.example.orderingm2.models.Order;
import com.example.orderingm2.models.OrderStatus;
import com.example.orderingm2.repositories.OrderRepo;
import com.example.orderingm2.services.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewOrder_ValidRequest_SuccessfulCreation() {
        // Arrange
        CreateNewOrderRequest request = new CreateNewOrderRequest();
        Order order = new Order();
        order.setId(1L);
        order.setCreationDate(LocalDateTime.now());

        when(orderMapper.map(request)).thenReturn(order);
        when(orderRepo.save(any(Order.class))).thenReturn(order);

        // Act
        GenericCreationResponse response = orderService.createNewOrder(request);

        // Assert
        assertNotNull(response);
        assertEquals(order.getId(), response.getId());
        assertEquals(order.getCreationDate(), response.getCreationDate());
    }

    @Test
    void createNewOrder_LogicalException_ExceptionThrown() {
        // Arrange
        CreateNewOrderRequest request = new CreateNewOrderRequest();

        when(orderMapper.map(request)).thenReturn(new Order());
        when(orderRepo.save(any(Order.class))).thenThrow(LogicalException.class);

        // Act & Assert
        assertThrows(LogicalException.class, () -> orderService.createNewOrder(request));
    }

    @Test
    void findOrder_ExistingOrderId_OrderDtoReturned() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.OPEN);
        order.setQuantity(BigDecimal.ONE);
        OrderDto expectedDto = new OrderDto(OrderStatus.OPEN,BigDecimal.ONE,new Currency());

        when(orderRepo.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.map(order)).thenReturn(expectedDto);

        // Act
        OrderDto result = orderService.findOrder(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto, result);
    }

    @Test
    void findOrder_NonExistingOrderId_LogicalExceptionThrown() {
        Long orderId = 1L;

        when(orderRepo.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(LogicalException.class, () -> orderService.findOrder(orderId));
    }

}
