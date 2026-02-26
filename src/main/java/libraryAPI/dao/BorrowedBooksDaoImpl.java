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
        String sql = "SELECT * FROM \"Library\".borrowed_books";
        return executeStatementList(result, sql);
    }

    @Override
    public List<BorrowedBook> getBorrowedBooksWithStatus(Status status) {
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
        String sql = "INSERT INTO \"Library\".borrowed_books (book_id, reader_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseHandler.getConnection()) {
            if (bookDao.bookExists(borrowedBook.getBook_id()) && readerDao.readerExists(borrowedBook.getReader_id())) {

                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setInt(1, borrowedBook.getBook_id());
                    stmt.setInt(2, borrowedBook.getReader_id());

                    // ИСПРАВЛЕНИЕ: Конвертация util.Date в sql.Date
                    if (borrowedBook.getBorrow_date() != null) {
                        stmt.setDate(3, new java.sql.Date(borrowedBook.getBorrow_date().getTime()));
                    } else {
                        stmt.setNull(3, java.sql.Types.DATE);
                    }

                    if (borrowedBook.getReturn_date() != null) {
                        stmt.setDate(4, new java.sql.Date(borrowedBook.getReturn_date().getTime()));
                    } else {
                        stmt.setNull(4, java.sql.Types.DATE);
                    }

                    // Сохраняем статус как строку (убедитесь, что метод toString() в классе Status возвращает нужное имя)
                    stmt.setString(5, borrowedBook.getStatus().getStatus());

                    int affectedRows = stmt.executeUpdate();

                    if (affectedRows > 0) {
                        try (ResultSet rs = stmt.getGeneratedKeys()) {
                            if (rs.next()) {
                                // Обычно в Postgres это колонка "borrow_id" или по индексу 1
                                borrowedBook.setId(rs.getInt(1));
                            }
                        }
                        return borrowedBook;
                    }
                }
            } else {
                System.out.println("Ошибка: Книга или Читатель не найдены в БД");
            }
        } catch (SQLException e) {
            // Обязательно выводим ошибку, чтобы видеть проблемы с БД (например, нарушения foreign key)
            e.printStackTrace();
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
                while (rs.next()) {
                    result.add(mapCurrentRowToBorrowed(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private BorrowedBook mapCurrentRowToBorrowed(ResultSet rs) throws SQLException {
        BorrowedBook bb = new BorrowedBook();
        bb.setId(rs.getInt("borrow_id")); // Убедитесь, что имя колонки в БД совпадает
        bb.setBook_id(rs.getInt("book_id"));
        bb.setReader_id(rs.getInt("reader_id"));
        bb.setBorrow_date(rs.getDate("borrow_date"));
        bb.setReturn_date(rs.getDate("return_date"));
        bb.setStatus(new Status(rs.getString("status")));
        return bb;
    }

    private List<BorrowedBook> executeStatementList(List<BorrowedBook> result, String sql) {
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(mapCurrentRowToBorrowed(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
