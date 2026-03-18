package com.example.dao;

import com.example.model.Author;
import com.example.model.Book;

import java.util.List;

public interface AuthorDao {

    void create(Author author);
    List<Author> findAll();
    void update(Author author);
    void delete(Author author);
    Author findById(int id);
    void create(List<Author> authors);
}
