package com.iprody.library.controller;

import com.iprody.library.entity.Book;
import com.iprody.library.entity.BorrowedBook;
import com.iprody.library.entity.Reader;
import com.iprody.library.repository.BorrowedBookRepository;

import java.sql.Date;
import java.util.List;

public class BorrowedBookController {
    private final BorrowedBookRepository borrowedBookRepository = new BorrowedBookRepository();

    public String createBook(Date borrowDate, Date returnDate, String status, Book book, Reader reader) {
        BorrowedBook borrowedBook = new BorrowedBook(borrowDate, returnDate, status, book, reader);
        borrowedBookRepository.save(borrowedBook);
        return "Взятая книга успешно сохранена!";
    }

    public List<BorrowedBook> getAllBooks() {
        return borrowedBookRepository.findAll();
    }

    public void printBookDetails(Long id) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(id);
        if (borrowedBook != null) {
            System.out.println(borrowedBook);
        } else {
            System.out.println("Взятая книга с ID " + id + " не найдена.");
        }
    }

    public void removeBorrowedBook(Long id) {
        borrowedBookRepository.delete(id);
        System.out.println("Книга удалена.");
    }
}
