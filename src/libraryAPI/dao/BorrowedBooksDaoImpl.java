package libraryAPI.dao;

import libraryAPI.DatabaseHandler;
import libraryAPI.model.Borrowed_book;
import libraryAPI.model.Reader;
import libraryAPI.model.Status;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBooksDaoImpl implements  BorrowedBooksDAO {

    @Override
    public List<Borrowed_book> getBorrowedBooks() {
        List<Borrowed_book> result = new ArrayList<>();
        String sql = "select * from \"Library\".borrowed_books";;
        return executeStatementList(result, sql);
    }

    @Override
    public List<Borrowed_book> getBorrowedBooksWithStatus(Enum<Status> status) {
        List<Borrowed_book> result = new ArrayList<>();
        String sql = String.format("select * from \"Library\".borrowed_books where status = '%s'", status.toString().toLowerCase());
        System.out.println(sql);
        return executeStatementList(result, sql);
    }

    @Override
    public List<Borrowed_book> findAllBorrowedAfterDate(Date date) {
        String dateString = date.toString().formatted(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Borrowed_book> result = new ArrayList<>();
        String sql = String.format("select * from \"Library\".borrowed_books where borrow_date > '%s'", dateString);
        System.out.println(sql);
        return executeStatementList(result, sql);
    }

    private List<Borrowed_book> executeStatementList(List<Borrowed_book> result, String sql) {
        try (PreparedStatement preparedStatement = DatabaseHandler.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Borrowed_book book = new Borrowed_book();
                book.setBook_id(resultSet.getInt("book_id"));
                book.setBorrow_date(resultSet.getDate("borrow_date"));
                book.setId(resultSet.getInt("borrow_id"));
                book.setReader_id(resultSet.getInt("reader_id"));
                book.setReturn_date(resultSet.getDate("return_date"));
                book.setStatus(Status.valueOf(resultSet.getString("status").toUpperCase()));
                result.add(book);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }


}
