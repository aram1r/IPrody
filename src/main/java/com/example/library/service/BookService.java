package com.example.library.service;


import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;
    UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    public Book updateBook(Long id, Book book) {
        Book tempBook = bookRepository.findBookById(id);
        if (tempBook!=null) {
            if (book.getBorrower()!=null) {
                tempBook.setBorrower(book.getBorrower());
            }
            tempBook.setIsbn(book.getIsbn());
            tempBook.setAuthors(book.getAuthors());
            tempBook.setTitle(book.getTitle());
            tempBook.setAvailable(book.isAvailable());
            bookRepository.save(tempBook);
        }
        return tempBook;
    }

    @Transactional
    public Book deleteBook(Long id) {
        return bookRepository.deleteBookById(id);
    }


    public Boolean borrowBook(Long id, Long userId) {
        return modifyBookAvailability(id, userId, true);
    }

    private Boolean modifyBookAvailability(Long id, Long userId, Boolean available) {
        User user = userRepository.findUserById(userId);
        Book book = bookRepository.findBookById(id);
        if (user!=null && book!=null && book.isAvailable()) {
            if (available) {
                user.getBorrowedBooks().add(book);
                book.setBorrower(user);
            } else {
                user.getBorrowedBooks().remove(book);
                book.setBorrower(null);
            }
            userRepository.save(user);
            book.setAvailable(available);
            bookRepository.save(book);
            return true;
        } else {
            return false;
        }
    }


    public Boolean returnBook(Long id, Long userId) {
        return modifyBookAvailability(id, userId, false);
    }
}
