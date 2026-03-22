package com.example.dao;

import com.example.model.Adress;

import java.util.List;

public interface AdressDao {
    void create(Adress adress);
    List<Adress> findAll();
    void update(Adress adress);
    void delete(Adress adress);
    Adress findById(int id);
    void create(List<Adress> adresses);
}
