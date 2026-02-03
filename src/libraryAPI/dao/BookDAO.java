package libraryAPI.dao;

import libraryAPI.model.Book;

public interface BookDAO {
    Book addBook(Book book);
    Book updateBook(Book book);
}
