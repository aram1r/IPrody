package com.example.main.service;

public class UsdCashierServiceImpl implements CashierService {
    @Override
    public String cash(Integer value) {
        return (value) + " usd";
    }
}
