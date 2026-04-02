package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private OrderService orderService = new OrderService();

    @Test
    void testGetAllOrder() {
        Order order = new Order();
        order.setOrderName("TestOrder");
        order.setCurrency("rub");
        order.setAmount(100d);

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> orders = orderService.getOrders();
        assertEquals(1, orders.size());
        assertEquals("TestOrder", order.getOrderName());
    }
}
