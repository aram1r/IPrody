package libraryAPI.dao;

import libraryAPI.DatabaseHandler;
import libraryAPI.model.BorrowedBook;
import libraryAPI.model.Status;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBooksDaoImpl implements  BorrowedBooksDAO {

    BookDAOImpl bookDao;
    ReaderDaoImpl readerDao;

    public BorrowedBooksDaoImpl() {
        bookDao = new BookDAOImpl();
        readerDao = new ReaderDaoImpl();
    }

    @Override
    public List<BorrowedBook> getBorrowedBooks() {
        List<BorrowedBook> result = new ArrayList<>();
        String sql = "select * from \"Library\".borrowed_books";;
        return executeStatementList(result, sql);
    }

    @Override
    public List<BorrowedBook> getBorrowedBooksWithStatus(Enum<Status> status) {
        List<BorrowedBook> result = new ArrayList<>();
        String sql = String.format("select * from \"Library\".borrowed_books where status = '%s'", status.toString().toLowerCase());
        System.out.println(sql);
        return executeStatementList(result, sql);
    }

    @Override
    public List<BorrowedBook> findAllBorrowedAfterDate(Date date) {
        String dateString = date.toString().formatted(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<BorrowedBook> result = new ArrayList<>();
        String sql = String.format("select * from \"Library\".borrowed_books where borrow_date > '%s'", dateString);
        System.out.println(sql);
        return executeStatementList(result, sql);
    }

    @Override
    public BorrowedBook addBorrowedBook(BorrowedBook borrowedBook) {
        String sql = "insert into \"Library\".borrowed_books (book_id, reader_id, borrow_date, return_date, status) values (?, ?, ?, ?, ?)";;
        try (Connection conn = DatabaseHandler.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (bookDao.bookExists(borrowedBook.getBook_id()) && readerDao.readerExists(borrowedBook.getReader_id())) {
                stmt.setInt(1, borrowedBook.getBook_id());
                stmt.setInt(2, borrowedBook.getReader_id());
                stmt.setDate(3, borrowedBook.getBorrow_date());
                stmt.setDate(4, borrowedBook.getReturn_date());
                stmt.setString(5, borrowedBook.getStatus().toString());
                ResultSet rs = stmt.executeQuery();
                return mapResultSetToBorrowed(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<BorrowedBook> getBorrowedByReaderId(Integer readerId) {
        List<BorrowedBook> result = new ArrayList<>();
        String sql = "SELECT * FROM \"Library\".borrowed_books WHERE reader_id = ?";

        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, readerId);

            try (ResultSet rs = stmt.executeQuery()) {
                // Используем цикл, так как книг может быть несколько
                while (rs.next()) {
                    BorrowedBook bb = new BorrowedBook();
                    bb.setId(rs.getInt("borrow_id"));
                    bb.setBook_id(rs.getInt("book_id"));
                    bb.setReader_id(rs.getInt("reader_id"));
                    bb.setBorrow_date(rs.getDate("borrow_date"));
                    bb.setReturn_date(rs.getDate("return_date"));
                    // .toUpperCase() на случай, если в БД регистр отличается
                    bb.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
                    result.add(bb);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка книг читателя", e);
        }
        return result;
    }

    private List<BorrowedBook> executeStatementList(List<BorrowedBook> result, String sql) {
        try (PreparedStatement preparedStatement = DatabaseHandler.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowedBook book = new BorrowedBook();
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

    private BorrowedBook mapResultSetToBorrowed(ResultSet rs) throws SQLException {
        if (rs.next()) {
            BorrowedBook bb = new BorrowedBook();
            bb.setId(rs.getInt("id"));
            bb.setBook_id(rs.getInt("book_id"));
            bb.setReader_id(rs.getInt("reader_id"));
            bb.setBorrow_date(rs.getDate("borrow_date"));
            bb.setReturn_date(rs.getDate("return_date"));
            bb.setStatus(Status.valueOf(rs.getString("status")));
            return bb;
        }
        return null;
    }


}
