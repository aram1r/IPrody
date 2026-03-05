package com.example.app.coffee.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Order {
    private Integer orderId;
    private String ClientName;
    private final Logger logger = LogManager.getLogger(Order.class);

    public Order() {}

    public Order(Integer orderId, String clientName) {
        logger.info(String.format("Order ID: %d, Client Name: %s", orderId, clientName));
        this.orderId = orderId;
        ClientName = clientName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }
}
