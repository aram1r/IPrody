package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController (BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id));
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<Boolean> borrowBook(@PathVariable Long id, @RequestParam Long userId) {
        boolean result = bookService.borrowBook(id, userId);
        return result ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Boolean> returnBook(@PathVariable Long id, @RequestParam Long userId) {
        boolean result = bookService.returnBook(id, userId);
        return result ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
    }
}
