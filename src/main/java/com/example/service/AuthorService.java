package com.example.service;

import com.example.model.Author;

import java.util.List;

public interface AuthorService {
    void create(Author author);
    List<Author> findAll();
    void update(Author author);
    void delete(Author author);
    Author findById(int id);
    void create(List<Author> authors);
}
