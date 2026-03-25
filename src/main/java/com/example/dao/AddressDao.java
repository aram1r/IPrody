package com.example.dao;

import com.example.model.Address;

import java.util.List;

public interface AddressDao {
    void create(Address address);
    List<Address> findAll();
    void update(Address address);
    void delete(Address address);
    Address findById(int id);
    void create(List<Address> addresses);
}
