package libraryAPI.dao;

import libraryAPI.model.Borrowed_book;
import libraryAPI.model.Status;

import java.sql.Date;
import java.util.List;

public interface BorrowedBooksDAO {
    List<Borrowed_book>  getBorrowedBooks();
    List<Borrowed_book> getBorrowedBooksWithStatus (Enum<Status> status);
    List<Borrowed_book> findAllBorrowedAfterDate(Date date);
}
