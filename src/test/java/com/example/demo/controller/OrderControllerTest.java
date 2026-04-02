package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(com.example.demo.controller.OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    Order order;

    @BeforeEach
    void setup() {
        order = new Order();
        order.setId(1);
        order.setOrderName("TestOrder");
        order.setAmount(200d);
        order.setCurrency("rub");
    }

    @Test
    void testGetAllOrders() throws Exception {
        Mockito.when(orderService.getOrders()).thenReturn(List.of(order));
        mockMvc.perform(get("/orders/getall")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderName").value("TestOrder"));
    }

    @Test
    void testFindOrderById() throws Exception {
        Mockito.when(orderService.getById(1L)).thenReturn(order);
        mockMvc.perform(get("/orders/getbyid/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}