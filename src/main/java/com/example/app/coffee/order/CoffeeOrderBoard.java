package com.example.app.coffee.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class CoffeeOrderBoard {
    private final Logger logger = LogManager.getLogger(CoffeeOrderBoard.class);
    List<Order> orders;
    int firstToDeliver;

    public CoffeeOrderBoard() {
        logger.info("CoffeeOrderBoard constructor");
        this.orders = new ArrayList<>();
        firstToDeliver = 1;
    }

    public void addOrder(String name) {
        logger.info("Adding order: " + name);
        Order newOrder = new Order();
        newOrder.setClientName(name);
        if (!orders.isEmpty()) {
            newOrder.setOrderId(orders.get(orders.size()-1).getOrderId()+1);
        } else {
            newOrder.setOrderId(1);
        }
        orders.add(newOrder);
    }

    public void deliver() {
        logger.info("Delivering first order");
       orders.remove(firstToDeliver);
       firstToDeliver++;
    }

    public void deliver(int orderId) {
        try {
            if (orderId < firstToDeliver) {
                logger.info("Order with id " + orderId + " is already delivered");
                System.out.println("Заказ уже выдан");
            } else if (orderId == firstToDeliver) {
                logger.info("Order with anyway closest to deliver " + orderId);
                deliver();
                System.out.println("Выдаём заказ " + orderId);
            } else if (orderId <= orders.size()) {
                logger.info("Delivering order with id " + orderId);
                orders.remove(orderId);
                System.out.println("Выдаём заказ " + orderId);
            } else {
                logger.info("No such order " + orderId);
                System.out.println("Такого заказа ещё нет");
            }
        } catch (Exception ex) {
            logger.error("Error while delivering order", ex);
        }
    }

    public void draw() {
        logger.info("Printing all orders");
        for (Order order : orders) {
            logger.info(order.toString());
            System.out.println(order.getClientName() + " " +  order.getOrderId());
        }
    }
}
