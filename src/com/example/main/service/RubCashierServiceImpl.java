package com.example.main.service;

public class RubCashierServiceImpl implements CashierService{
    @Override
    public String cash(Integer value) {
        return (value) + " рублей";
    }
}
