package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> findAll();
    void update(User user);
    void delete(User user);
    User findById(int id);
    void create(List<User> users);
}