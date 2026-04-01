package com.example.main.repository;

import com.example.main.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(long id);

    Order findOrderByOrderName(String name);
}
