package com.iprody.library.controller;

import com.iprody.library.entity.Book;
import com.iprody.library.repository.BookRepository;

import java.util.List;

public class BookController {

    private final BookRepository bookRepository = new BookRepository();

    public String createBook(String title, String author, Integer year, String genre) {
        Book book = new Book(title, author, year, genre);
        bookRepository.save(book);
        return "Книга успешно сохранена!";
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void printBookDetails(Long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Книга с ID " + id + " не найдена.");
        }
    }

    public void removeBook(Long id) {
        bookRepository.delete(id);
        System.out.println("Книга удалена.");
    }
}