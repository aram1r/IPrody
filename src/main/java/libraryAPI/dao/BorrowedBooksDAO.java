package libraryAPI.dao;

import libraryAPI.model.BorrowedBook;
import libraryAPI.model.Reader;
import libraryAPI.model.Status;

import java.sql.Date;
import java.util.List;

public interface BorrowedBooksDAO {
    List<BorrowedBook> getBorrowedBooks();
    List<BorrowedBook> getBorrowedBooksWithStatus (Status status);
    List<BorrowedBook> findAllBorrowedAfterDate(Date date);
    BorrowedBook addBorrowedBook(BorrowedBook borrowedBook);
    List<BorrowedBook> getBorrowedByReaderId(Integer readerId);
}
