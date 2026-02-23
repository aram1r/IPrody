package libraryAPI.dao;

import libraryAPI.model.Book;

import java.sql.SQLException;

public interface BookDAO {
    Book addBook(Book book) throws SQLException;
    Book updateBook(Book book);
    Boolean bookExists(Integer bookId) throws SQLException;

}
