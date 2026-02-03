import libraryAPI.dao.BookDAOImpl;
import libraryAPI.dao.BorrowedBooksDaoImpl;
import libraryAPI.dao.ReaderDaoImpl;
import libraryAPI.model.Book;
import libraryAPI.model.Borrowed_book;
import libraryAPI.model.Reader;
import libraryAPI.model.Status;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Main{
    public static void main(String[] args) {
//        ReaderDaoImpl readerDao = new ReaderDaoImpl();
//        Reader reader = new Reader("Alex", "emaile", "12345678");
//        readerDao.addReader(reader);
//        BorrowedBooksDaoImpl borrowedBooksDaoImpl = new BorrowedBooksDaoImpl();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2023, 10, 06);
//
//        List<Borrowed_book> list = borrowedBooksDaoImpl.findAllBorrowedAfterDate(new java.sql.Date(calendar.getTime().getTime()));
//        for (Borrowed_book book : list) {
//            System.out.println(book.getBorrow_date());
//        }

        Book book = new Book(1, "Хроники Амбера", "Желязны", 1996, "Фэнтэзи");
        BookDAOImpl bookDAO = new BookDAOImpl();
        bookDAO.updateBook(book);
    }
}