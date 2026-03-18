package com.example.dao;

import com.example.model.Book;

import java.util.List;

public interface BookDao {

    void create(Book book);
    void update(Book book);
    void delete(int id);
    List<Book> findByAuthorId(int id);
    List<Book> findAll();
}
