package com.example.service;

import com.example.model.Address;

import java.util.List;

public interface AddressService {
    void create(Address address);
    List<Address> findAll();
    void update(Address adress);
    void delete(Address adress);
    Address findById(int id);
    void create(List<Address> addresses);
}
