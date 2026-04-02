package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderName("TestOrder");
        order.setAmount(200d);
        order.setCurrency("rub");
        orderRepository.save(order);
    }

    @Test
    void testFindById() {
        Optional<Order> foundOrder = orderRepository.findById(order.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals("TestOrder", foundOrder.get().getOrderName());
    }

    @Test
    void testFindByName() {
        Optional<Order> foundOrder = orderRepository.findOrderByOrderName("TestOrder");

        assertTrue(foundOrder.isPresent());
        assertEquals(order.getId(), foundOrder.get().getId());
    }

    @AfterEach
    void tearDown() {
        orderRepository.delete(order);
    }
}
