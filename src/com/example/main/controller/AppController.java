package com.example.main.controller;

import com.example.main.service.AppService;
import com.example.main.service.CashierService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final AppService appService;
    private final CashierService cashierService;

    public AppController(AppService appService, CashierService cashierService) {
        this.appService = appService;
        this.cashierService = cashierService;
    }

    @GetMapping("/greet")
    public String greet() {
        return appService.greet();
    }

    @PostMapping("/cash/{amount}")
    public String cash(@PathVariable("amount") Integer value) {
        return cashierService.cash(value);
    }
}
