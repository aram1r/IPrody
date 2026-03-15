package com.iprody.library.resource;


import com.iprody.library.entity.Book;
import com.iprody.library.entity.Reader;

import java.util.List;

public interface BookResourceInterface {
    Book addBook(Book book);
    List<Book> getBorrowedBooksById(String id);
}