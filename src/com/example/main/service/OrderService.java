package com.example.main.service;

import com.example.main.entity.Order;
import com.example.main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getByName(String name) {
        return orderRepository.findOrderByOrderName(name);
    }

    public Order getById(Long id) {
        return orderRepository.findOrderById(id);
    }
}
